package server.spring.api.common.config.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import server.spring.api.common.enums.DatePattern;

@NoArgsConstructor
public class ModelMapperBean {

    @Bean
    public ModelMapper mapper() {
        ModelMapper mm = new ModelMapper();

        mm.addConverter(
            new AbstractConverter<Date, String>() {
                @Override
                protected String convert(Date source) {
                    if (source == null) {
                        return "";
                    }
                    DateFormat format = new SimpleDateFormat(DatePattern.DATE_TIME_PATTERN);
                    return format.format(source);
                }
            });

        mm.addConverter(
            new AbstractConverter<LocalDateTime, String>() {
                @Override
                protected String convert(LocalDateTime source) {
                    if (source == null) {
                        return "";
                    }
                    return source.format(DateTimeFormatter.ofPattern(DatePattern.DATE_TIME_PATTERN));
                }
            });

        mm.addConverter(
            new AbstractConverter<LocalDate, String>() {
                @Override
                protected String convert(LocalDate source) {
                    if (source == null) {
                        return "";
                    }
                    return source.format(DateTimeFormatter.ofPattern(DatePattern.DATE_DAY_PATTERN));
                }
            });

        /** Java 8 Datetime * */
        mm.addConverter(
            new AbstractConverter<Date, LocalDate>() {
                @Override
                protected LocalDate convert(Date source) {
                    return source == null
                        ? null
                        : source.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }
            });
        mm.addConverter(
            new AbstractConverter<LocalDate, Date>() {
                @Override
                protected Date convert(LocalDate source) {
                    return source == null
                        ? null
                        : Date.from(source.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                }
            });
        mm.addConverter(
            new AbstractConverter<Date, LocalDateTime>() {
                @Override
                protected LocalDateTime convert(Date source) {
                    return source == null
                        ? null
                        : source.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                }
            });
        mm.addConverter(
            new AbstractConverter<LocalDateTime, Date>() {
                @Override
                protected Date convert(LocalDateTime source) {
                    return source == null
                        ? null
                        : Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
                }
            });

        mm.getConfiguration()
            .setFieldMatchingEnabled(true)
            // 매핑 조건
            .setPropertyCondition(Conditions.isNotNull())
            // 둘 이상의 소스 속성과 일치하는 대상 속성 무시 여부 (default false)
            .setAmbiguityIgnored(true);
        return mm;
    }

    /**
    * list Mapping
    */
    public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
    return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
    }

    public <D, T> D map(final T entity, Class<D> outClass) {

        return mapper().map(entity, outClass);
    }
}
