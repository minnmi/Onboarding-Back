package br.com.totemti.onboardingback.configurations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfiguration {
    private static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd");

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(JavaTimeModule javaTimeModule) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new ParameterNamesModule()).registerModule(new Jdk8Module())
                .registerModule(javaTimeModule);
        return new MappingJackson2HttpMessageConverter(mapper);
    }

    @Bean
    public JavaTimeModule javaTimeModule() {
        final LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(YYYY_MM_DD_HH_MM_SS);
        final LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(YYYY_MM_DD);
        final LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(YYYY_MM_DD_HH_MM_SS);
        final LocalDateSerializer localDateSerializer = new LocalDateSerializer(YYYY_MM_DD);

        final JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        javaTimeModule.addDeserializer(LocalDate.class, localDateDeserializer);
        javaTimeModule.addSerializer(localDateTimeSerializer);
        javaTimeModule.addSerializer(localDateSerializer);

        return javaTimeModule;
    }
}
