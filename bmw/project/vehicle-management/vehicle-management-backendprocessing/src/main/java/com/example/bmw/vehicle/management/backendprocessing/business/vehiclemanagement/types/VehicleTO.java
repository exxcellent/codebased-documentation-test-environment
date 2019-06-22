package com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.types;

public class VehicleTO {

    private String name;
    private int vehicleId;

    public VehicleTO() {

    }

    @Override
    public String toString() {
        return "VehicleTO{" +
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
