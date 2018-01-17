package com.codecool.freefoodmeetup.common;

import com.codecool.freefoodmeetup.exceptions.WrongValueException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@JsonComponent
public class LocalDateTimeCombinedSerializer {
    public static class LocalTimeDateDeserializer extends JsonDeserializer<LocalDateTime> {
        private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        @Override
        public LocalDateTime deserialize(JsonParser jsonParser,
                                         DeserializationContext deserializationContext) throws IOException {
            try {
                return LocalDateTime.parse(jsonParser.getValueAsString(), FORMATTER);
            } catch (DateTimeParseException e) {
                throw new WrongValueException("Date should have format: yyyy-MM-dd HH:mm");
            }
        }
    }

    public static class LocalTimeDateSerializer extends JsonSerializer<LocalDateTime> {
        private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(localDateTime.format(FORMATTER));
        }

    }
}
