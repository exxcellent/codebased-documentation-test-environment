package com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.types;

import java.time.OffsetDateTime;

public class VehicleNotificationReportTO {
    private int vehicleId;
    private boolean notificationSend;
    private OffsetDateTime messageSendAt;


    @Override
    public String toString() {
        return "VehicleNotificationReportTO{" +
                "vehicleId=" + vehicleId +
                ", notificationSend=" + notificationSend +
                ", messageSendAt=" + messageSendAt +
                '}';
    }


    public int getVehicleId() {
        return vehicleId;
    }


    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }


    public boolean isNotificationSend() {
        return notificationSend;
    }


    public void setNotificationSend(boolean notificationSend) {
        this.notificationSend = notificationSend;
    }


    public OffsetDateTime getMessageSendAt() {
        return messageSendAt;
    }


    public void setMessageSendAt(OffsetDateTime messageSendAt) {
        this.messageSendAt = messageSendAt;
    }
}
