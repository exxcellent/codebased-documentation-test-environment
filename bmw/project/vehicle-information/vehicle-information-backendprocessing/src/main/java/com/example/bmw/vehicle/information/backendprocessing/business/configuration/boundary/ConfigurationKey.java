//======================================================================================================================
// Module: BMW Remote Software Update (RSU) - Zentrales Fahrzeug Update System (ZFUS)
// Copyright (c) 2017 BMW Group. All rights reserved.
//======================================================================================================================
package com.example.bmw.vehicle.information.backendprocessing.business.configuration.boundary;

public enum ConfigurationKey {

    VEHICLE_INFORMATION_BASE_URL("VEHICLE_SERVICES_BASE_URL"),
    VEHICLE_INFORMATION_SUB_URL("VEHICLE_SERVICES_SUB_URL");

    private final String value;


    ConfigurationKey(final String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
