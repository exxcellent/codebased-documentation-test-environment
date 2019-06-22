package com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.boundary;

import javax.inject.Inject;
import com.example.bmw.common.validation.BmwPreconditions;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.control.VehicleNotificationHandlerBA;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.types.VehicleNotificationRequestTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;

public class VehicleNotificationBF implements VehicleNotificationBCI {

    @Inject
    private VehicleNotificationHandlerBA vehicleInformationBA;


    @Override
    public VehicleNotificationResponseTO sendVehicleNotification(
            VehicleNotificationRequestTO vehicleNotificationRequestTO) {
        BmwPreconditions.checkNotNull(vehicleNotificationRequestTO, "VehicleNotificationRequestTO must not be null");
        BmwPreconditions.checkNotNullOrEmpty(vehicleNotificationRequestTO.getMessage(), "Message must not be null");
        BmwPreconditions.checkArgument(vehicleNotificationRequestTO.getVehicleId() > 0, "VehicleId must not be null");

        return vehicleInformationBA.sendNotification(vehicleNotificationRequestTO);
    }
}
