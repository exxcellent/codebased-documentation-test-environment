package com.example.bmw.vehicle.services.webservice.common.json;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class OffsetDateTimeGsonConverter implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    public static final Type OFFSET_DATE_TIME_TYPE = new TypeToken<OffsetDateTime>() {
    }.getType();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;


    @Override
    public JsonElement serialize(
            final OffsetDateTime dateTime, final Type typeOfDateTime, final JsonSerializationContext context) {
        return dateTime == null ? null : new JsonPrimitive(FORMATTER.format(dateTime));
    }


    @Override
    public OffsetDateTime deserialize(
            final JsonElement json, final Type typeOfDateTime, final JsonDeserializationContext context)
            throws JsonParseException {
        return json == null ? null : FORMATTER.parse(json.getAsString(), Dates.temporalToUTCDateTimeConverter());
    }

}
