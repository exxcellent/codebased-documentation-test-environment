package com.example.bmw.vehicle.management.backendprocessing.business.configuration.boundary;

public enum ConfigurationKey {

    VEHICLE_INFORMATION_BASE_URL("VEHICLE_INFORMATION_BASE_URL"),
    VEHICLE_INFORMATION_SUB_URL("VEHICLE_INFORMATION_SUB_URL"),
    VEHICLE_SERVICES_BASE_URL("VEHICLE_SERVICES_BASE_URL");
    //VEHICLE_INFORMATION_SUB_URL("VEHICLE_INFORMATION_SUB_URL");

    private final String value;


    ConfigurationKey(final String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
