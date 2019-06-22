package com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.boundary;

import javax.inject.Inject;
import com.example.bmw.common.validation.Preconditions;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.control.VehicleNotificationBA;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationTO;

public class VehicleNotificationBF implements VehicleNotificationBCI {

    @Inject
    private VehicleNotificationBA vehicleNotificationBA;

    @Override
    public VehicleNotificationTO triggerNotification(int vehicleId, String message) {
        Preconditions.checkArgument(vehicleId > 0, "VehicleID must not be null");
        Preconditions.checkNotNullOrEmpty(message, "Message must not be null");

        return vehicleNotificationBA.triggerNotification(vehicleId, message);
    }
}
