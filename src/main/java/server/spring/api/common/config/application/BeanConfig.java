package server.spring.api.common.config.application;//package server.spring.setting.common.config.application;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import org.jasypt.encryption.StringEncryptor;
//import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
//import org.modelmapper.AbstractConverter;
//import org.modelmapper.Conditions;
//import org.modelmapper.ModelMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.Formatter;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.Locale;
//
//@Configuration
//public class BeanConfig {
//
//    //    private static final String DATE_TIME_OFFSET_PATTERN = "yyyy-MM-dd[['T'][
//    // ]HH:mm:ss[.SSS][zz][ZZ]]";
//    private static final String DATE_TIME_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
//    private static final String TIME_OFFSET_PATTERN = "HH:mm:ss[.SSS][zz][ZZ]";
//    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
//    private static final String DATE_PATTERN = "yyyy-MM-dd";
//    private static final String TIME_PATTERN = "HH:mm:ss";
//
//    //    @Bean
//    //    public ObjectMapper objectMapper() {
//    //        ObjectMapper mapper = new ObjectMapper();
//    //        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//    //        mapper.registerModule(jsonMapperJava8DateTimeModule());
//    //        return mapper;
//    //    }
//
//
//    @Bean
//    public Module jsonMapperJava8DateTimeModule() {
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(
//                LocalDate.class,
//                new JsonSerializer<LocalDate>() {
//                    @Override
//                    public void serialize(
//                            LocalDate localDate,
//                            JsonGenerator jsonGenerator,
//                            SerializerProvider serializerProvider)
//                            throws IOException {
//                        jsonGenerator.writeString(DateTimeFormatter.ofPattern(DATE_PATTERN).format(localDate));
//                    }
//                });
//        module.addSerializer(
//                LocalTime.class,
//                new JsonSerializer<LocalTime>() {
//                    @Override
//                    public void serialize(
//                            LocalTime localTime,
//                            JsonGenerator jsonGenerator,
//                            SerializerProvider serializerProvider)
//                            throws IOException {
//                        jsonGenerator.writeString(DateTimeFormatter.ofPattern(TIME_PATTERN).format(localTime));
//                    }
//                });
//        module.addSerializer(
//                LocalDateTime.class,
//                new JsonSerializer<LocalDateTime>() {
//                    @Override
//                    public void serialize(
//                            LocalDateTime localDateTime,
//                            JsonGenerator jsonGenerator,
//                            SerializerProvider serializerProvider)
//                            throws IOException {
//                        jsonGenerator.writeString(
//                                DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(localDateTime));
//                    }
//                });
//        module.addSerializer(
//                Date.class,
//                new JsonSerializer<Date>() {
//                    private SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
//
//                    @Override
//                    public void serialize(
//                            Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
//                            throws IOException {
//                        jsonGenerator.writeString(format.format(date));
//                    }
//                });
//
//        module.addDeserializer(
//                LocalDate.class,
//                new JsonDeserializer<LocalDate>() {
//                    @Override
//                    public LocalDate deserialize(
//                            JsonParser jsonParser, DeserializationContext deserializationContext)
//                            throws IOException {
//                        return LocalDate.parse(
//                                jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(DATE_PATTERN));
//                    }
//                });
//        module.addDeserializer(
//                LocalTime.class,
//                new JsonDeserializer<LocalTime>() {
//                    @Override
//                    public LocalTime deserialize(
//                            JsonParser jsonParser, DeserializationContext deserializationContext)
//                            throws IOException {
//                        return LocalTime.parse(
//                                jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(TIME_PATTERN));
//                    }
//                });
//        module.addDeserializer(
//                LocalDateTime.class,
//                new JsonDeserializer<LocalDateTime>() {
//                    @Override
//                    public LocalDateTime deserialize(
//                            JsonParser jsonParser, DeserializationContext deserializationContext)
//                            throws IOException {
//                        return LocalDateTime.parse(
//                                jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
//                    }
//                });
//        module.addDeserializer(
//                Date.class,
//                new JsonDeserializer<Date>() {
//                    private SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
//
//                    @Override
//                    public Date deserialize(
//                            JsonParser jsonParser, DeserializationContext deserializationContext)
//                            throws IOException {
//                        try {
//                            return format.parse(jsonParser.getValueAsString());
//                        } catch (ParseException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                });
//        module.addDeserializer(
//                String.class,
//                new JsonDeserializer<String>() {
//                    @Override
//                    public String deserialize(
//                            JsonParser jsonParser, DeserializationContext deserializationContext)
//                            throws IOException {
//                        if (jsonParser.getValueAsString().isEmpty()) {
//                            return null;
//                        }
//
//                        return jsonParser.getValueAsString();
//                    }
//                });
//        return module;
//    }
//
//    @Bean
//    public ModelMapper mapper() {
//        ModelMapper mm = new ModelMapper();
//
//        mm.addConverter(
//                new AbstractConverter<Date, String>() {
//                    @Override
//                    protected String convert(Date source) {
//                        if (source == null) {
//                            return "";
//                        }
//                        DateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
//                        return format.format(source);
//                    }
//                });
//
//        mm.addConverter(
//                new AbstractConverter<LocalDateTime, String>() {
//                    @Override
//                    protected String convert(LocalDateTime source) {
//                        if (source == null) {
//                            return "";
//                        }
//                        return source.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
//                    }
//                });
//
//        mm.addConverter(
//                new AbstractConverter<LocalDate, String>() {
//                    @Override
//                    protected String convert(LocalDate source) {
//                        if (source == null) {
//                            return "";
//                        }
//                        return source.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
//                    }
//                });
//
//        /** Java 8 Datetime * */
//        mm.addConverter(
//                new AbstractConverter<Date, LocalDate>() {
//                    @Override
//                    protected LocalDate convert(Date source) {
//                        return source == null
//                                ? null
//                                : source.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                    }
//                });
//        mm.addConverter(
//                new AbstractConverter<LocalDate, Date>() {
//                    @Override
//                    protected Date convert(LocalDate source) {
//                        return source == null
//                                ? null
//                                : Date.from(source.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//                    }
//                });
//        mm.addConverter(
//                new AbstractConverter<Date, LocalDateTime>() {
//                    @Override
//                    protected LocalDateTime convert(Date source) {
//                        return source == null
//                                ? null
//                                : source.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//                    }
//                });
//        mm.addConverter(
//                new AbstractConverter<LocalDateTime, Date>() {
//                    @Override
//                    protected Date convert(LocalDateTime source) {
//                        return source == null
//                                ? null
//                                : Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
//                    }
//                });
//
//        mm.getConfiguration()
//                .setFieldMatchingEnabled(true)
//                .setPropertyCondition(Conditions.isNotNull())
//                .setAmbiguityIgnored(true)
//                .setImplicitMappingEnabled(true);
//              //  .setMatchingStrategy(MatchingStrategies.STRICT);
//
//        //                .setDeepCopyEnabled(true);
//        //                .setSourceNameTransformer(NameTransformers.JAVABEANS_MUTATOR)
//        //                .setDestinationNameTransformer(NameTransformers.JAVABEANS_MUTATOR);
//        //                .setMatchingStrategy(MatchingStrategies.STRICT);
//        //
//        // .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
//        //                .setSourceNamingConvention(NamingConventions.JAVABEANS_ACCESSOR);
//
//        return mm;
//    }
//
//    @Bean
//    public Formatter<LocalDateTime> localDateTimeFormatter() {
//        return new Formatter<LocalDateTime>() {
//            @Override
//            public LocalDateTime parse(String text, Locale locale) throws ParseException {
//                //                if(text.contains("T")) {
//                //                    return LocalDateTime.parse(text,
//                // DateTimeFormatter.ofPattern(DATE_TIME_OFFSET_PATTERN));
//                //                } else
//                if (text.contains(":")) {
//                    return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DATE_TIME_OFFSET_PATTERN));
//                } else {
//                    return LocalDate.parse(text, DateTimeFormatter.ofPattern(DATE_PATTERN))
//                            .atTime(LocalTime.MIDNIGHT);
//                }
//            }
//
//            @Override
//            public String print(LocalDateTime object, Locale locale) {
//                return DateTimeFormatter.ofPattern(DATE_TIME_OFFSET_PATTERN).format(object);
//            }
//        };
//    }
//
//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }
//
//    @Bean
//    public StringEncryptor encryptor() {
//        StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
//        enc.setPassword("akaakaajrwk20!@#");
//        return enc;
//    }
//}
