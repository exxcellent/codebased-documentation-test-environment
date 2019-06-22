package com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.types;

public class VehicleInformationTO {

    private int vehicleId;
private String vehicleName;

    public VehicleInformationTO() {

    }


    @Override
    public String toString() {
        return "VehicleInformationTO{" +
                "vehicleId=" + vehicleId +
                ", vehicleName='" + vehicleName + '\'' +
                '}';
    }


    public int getVehicleId() {
        return vehicleId;
    }


    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }


    public String getVehicleName() {
        return vehicleName;
    }


    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
