package server.spring.api.common.config.util;

import static server.spring.api.common.enums.DatePattern.DATE_DAY_PATTERN;
import static server.spring.api.common.enums.DatePattern.DATE_TIME_OFFSET_PATTERN;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.format.Formatter;

public class LocalDateTimeFormatterBean {
    // Date String 변환 util
    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        Formatter<LocalDateTime> formatter = new Formatter<>() {
            @Override
            public LocalDateTime parse(String text, Locale locale)  {
                if (text.contains(":")) {
                    return LocalDateTime.parse(text,
                        DateTimeFormatter.ofPattern(DATE_TIME_OFFSET_PATTERN));
                } else {
                    return LocalDate.parse(text, DateTimeFormatter.ofPattern(DATE_DAY_PATTERN))
                        .atTime(LocalTime.MIDNIGHT);
                }
            }

            @Override
            public String print(LocalDateTime object, Locale locale) {
                return DateTimeFormatter.ofPattern(DATE_TIME_OFFSET_PATTERN).format(object);
            }
        };
        return formatter;
    }

}
