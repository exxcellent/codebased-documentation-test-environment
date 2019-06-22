package com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types;

import java.time.OffsetDateTime;

public class VehicleNotificationResponseTO {

    private String vehicleName;
    private int vehicleIdentification;
    private boolean notificationSend;
    private String message;
    private OffsetDateTime notificationSendTime;


    @Override
    public String toString() {
        return "VehicleNotificationResponseTO{" +
                "vehicleName='" + vehicleName + '\'' +
                ", vehicleIdentification=" + vehicleIdentification +
                ", notificationSend=" + notificationSend +
                ", message='" + message + '\'' +
                ", notificationSendTime=" + notificationSendTime +
                '}';
    }


    public String getVehicleName() {
        return vehicleName;
    }


    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }


    public int getVehicleIdentification() {
        return vehicleIdentification;
    }


    public void setVehicleIdentification(int vehicleIdentification) {
        this.vehicleIdentification = vehicleIdentification;
    }


    public boolean isNotificationSend() {
        return notificationSend;
    }


    public void setNotificationSend(boolean notificationSend) {
        this.notificationSend = notificationSend;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public OffsetDateTime getNotificationSendTime() {
        return notificationSendTime;
    }


    public void setNotificationSendTime(OffsetDateTime notificationSendTime) {
        this.notificationSendTime = notificationSendTime;
    }
}
