package com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.types;


public class VehicleNotificationTO {

    private int vehicleId;
    private String name;
    private String message;


    @Override
    public String toString() {
        return "VehicleNotificationTO{" +
                "vehicleId=" + vehicleId +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


    public int getVehicleId() {
        return vehicleId;
    }


    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}
