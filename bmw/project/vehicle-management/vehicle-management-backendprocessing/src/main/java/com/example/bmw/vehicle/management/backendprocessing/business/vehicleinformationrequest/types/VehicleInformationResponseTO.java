package com.example.bmw.vehicle.management.backendprocessing.business.vehicleinformationrequest.types;

public class VehicleInformationResponseTO {
    private String name;
    private int vehicleId;


    @Override
    public String toString() {
        return "VehicleInformationResponseTO{" +
                "name='" + name + '\'' +
                ", vehicleId=" + vehicleId +
                '}';
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getVehicleId() {
        return vehicleId;
    }


    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
