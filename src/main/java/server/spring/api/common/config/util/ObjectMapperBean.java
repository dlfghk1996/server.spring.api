package server.spring.api.common.config.util;

import static server.spring.api.common.enums.DatePattern.DATE_DAY_PATTERN;
import static server.spring.api.common.enums.DatePattern.DATE_TIME_PATTERN;
import static server.spring.api.common.enums.DatePattern.TIME_PATTERN;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.context.annotation.Bean;

public class ObjectMapperBean {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(jsonMapperJava8DateTimeModule());
        return mapper;
    }

    // Custom Serializer on the ObjectMapper
    @Bean
    public Module jsonMapperJava8DateTimeModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(
            LocalDate.class,
            new JsonSerializer<LocalDate>() {
                @Override
                public void serialize(
                    LocalDate localDate,
                    JsonGenerator jsonGenerator,
                    SerializerProvider serializerProvider)
                    throws IOException {
                    jsonGenerator.writeString(DateTimeFormatter.ofPattern(DATE_DAY_PATTERN).format(localDate));
                }
            });
        module.addSerializer(
            LocalTime.class,
            new JsonSerializer<LocalTime>() {
                @Override
                public void serialize(
                    LocalTime localTime,
                    JsonGenerator jsonGenerator,
                    SerializerProvider serializerProvider)
                    throws IOException {
                    jsonGenerator.writeString(DateTimeFormatter.ofPattern(TIME_PATTERN).format(localTime));
                }
            });
        module.addSerializer(
            LocalDateTime.class,
            new JsonSerializer<LocalDateTime>() {
                @Override
                public void serialize(
                    LocalDateTime localDateTime,
                    JsonGenerator jsonGenerator,
                    SerializerProvider serializerProvider)
                    throws IOException {
                    jsonGenerator.writeString(
                        DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(localDateTime));
                }
            });
        module.addSerializer(
            Date.class,
            new JsonSerializer<Date>() {
                private SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);

                @Override
                public void serialize(
                    Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                    throws IOException {
                    jsonGenerator.writeString(format.format(date));
                }
            });

        module.addDeserializer(
            LocalDate.class,
            new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(
                    JsonParser jsonParser, DeserializationContext deserializationContext)
                    throws IOException {
                    return LocalDate.parse(
                        jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(DATE_DAY_PATTERN));
                }
            });
        module.addDeserializer(
            LocalTime.class,
            new JsonDeserializer<LocalTime>() {
                @Override
                public LocalTime deserialize(
                    JsonParser jsonParser, DeserializationContext deserializationContext)
                    throws IOException {
                    return LocalTime.parse(
                        jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(TIME_PATTERN));
                }
            });
        module.addDeserializer(
            LocalDateTime.class,
            new JsonDeserializer<LocalDateTime>() {
                @Override
                public LocalDateTime deserialize(
                    JsonParser jsonParser, DeserializationContext deserializationContext)
                    throws IOException {
                    return LocalDateTime.parse(
                        jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
                }
            });
        module.addDeserializer(
            Date.class,
            new JsonDeserializer<Date>() {
                private SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);

                @Override
                public Date deserialize(
                    JsonParser jsonParser, DeserializationContext deserializationContext)
                    throws IOException {
                    try {
                        return format.parse(jsonParser.getValueAsString());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        module.addDeserializer(
            String.class,
            new JsonDeserializer<String>() {
                @Override
                public String deserialize(
                    JsonParser jsonParser, DeserializationContext deserializationContext)
                    throws IOException {
                    if (jsonParser.getValueAsString().isEmpty()) {
                        return null;
                    }

                    return jsonParser.getValueAsString();
                }
            });
        return module;
    }
}
