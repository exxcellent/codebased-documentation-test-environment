package com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.boundary;

import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationTO;

public interface VehicleNotificationBCI {
    VehicleNotificationTO triggerNotification(int vehicleId, String message);
}
