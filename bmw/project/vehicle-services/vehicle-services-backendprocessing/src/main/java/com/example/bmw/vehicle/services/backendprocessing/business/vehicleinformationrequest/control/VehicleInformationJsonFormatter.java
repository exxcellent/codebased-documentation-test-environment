package com.example.bmw.vehicle.services.backendprocessing.business.vehicleinformationrequest.control;

import com.example.bmw.vehicle.services.backendprocessing.business.vehicleinformationrequest.types.VehicleInformationResponseTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class VehicleInformationJsonFormatter {

    public VehicleInformationResponseTO fromJson(final String response) {
        return gson().fromJson(response, VehicleInformationResponseTO.class);
    }


    private Gson gson() {
        return new GsonBuilder().create();
    }}
