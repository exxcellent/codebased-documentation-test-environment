package com.example.bmw.vehicle.services.webservice.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonMapper {

    /**
     * Standard empty constructor, nothing special
     */
    public JsonMapper() {
        // standard constructor needed for initialization
    }


    /**
     * Creates Json from TO. Is never null.
     */
    public <T> String asJsonString(final T transferObject) {
        return gson().toJson(transferObject, transferObject.getClass());
    }


    /**
     * Creates TO from Json. Is never null.
     */
    public <T> T fromJson(final String json, final Class<T> clazz) {
        return gson().fromJson(json, clazz);
    }


    private Gson gson() {
        return new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(
                        OffsetDateTimeGsonConverter.OFFSET_DATE_TIME_TYPE, new OffsetDateTimeGsonConverter())
                .create();
    }
}
