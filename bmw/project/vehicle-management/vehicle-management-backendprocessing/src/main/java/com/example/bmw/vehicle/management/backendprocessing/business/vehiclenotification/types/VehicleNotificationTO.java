package com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types;

public class VehicleNotificationTO {

    private String vehicleName;
    private int vehicleId;
    private String message;
    private String timestamp;


    @Override
    public String toString() {
        return "VehicleNotificationTO{" +
                "vehicleName='" + vehicleName + '\'' +
                ", vehicleId=" + vehicleId +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }


    public String getVehicleName() {
        return vehicleName;
    }


    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
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


    public String getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
