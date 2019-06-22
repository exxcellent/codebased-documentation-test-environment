package com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types;


public class VehicleNotificationRequestTO {

    private int vehicleId;
    private String message;


    @Override
    public String toString() {
        return "VehicleNotificationTO{" +
                "vehicleId=" + vehicleId +
                ", message='" + message + '\'' +
                '}';
    }


    public int getVehicleId() {
        return vehicleId;
    }


    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}
