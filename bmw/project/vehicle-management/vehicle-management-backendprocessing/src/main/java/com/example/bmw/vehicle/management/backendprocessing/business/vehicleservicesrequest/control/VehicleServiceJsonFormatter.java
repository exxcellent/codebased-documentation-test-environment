package com.example.bmw.vehicle.management.backendprocessing.business.vehicleservicesrequest.control;

import java.time.OffsetDateTime;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class VehicleServiceJsonFormatter {

    public VehicleNotificationResponseTO fromJson(final String response) {
        return gson().fromJson(response, VehicleNotificationResponseTO.class);
    }


    private Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(OffsetDateTimeGsonConverter.OFFSET_DATE_TIME_TYPE,
                new OffsetDateTimeGsonConverter())
                .create();
    }}
