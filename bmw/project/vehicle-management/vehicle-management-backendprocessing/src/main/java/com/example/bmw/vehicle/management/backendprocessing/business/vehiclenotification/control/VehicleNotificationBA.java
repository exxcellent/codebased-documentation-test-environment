package com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.control;

import javax.inject.Inject;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.mapper.VehicleNotificationMapper;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationRequestTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehicleservicesrequest.boundary.VehicleServicesRequestESI;

public class VehicleNotificationBA {

    @Inject
    private VehicleNotificationMapper vehicleNotificationMapper;

    @Inject
    private VehicleServicesRequestESI vehicleServicesRequestESI;

    public VehicleNotificationTO triggerNotification(int vehicleId, String message) {

        VehicleNotificationRequestTO vehicleNotificationRequestTO = new VehicleNotificationRequestTO();

        vehicleNotificationRequestTO.setVehicleId(vehicleId);
        vehicleNotificationRequestTO.setMessage(message);

        VehicleNotificationResponseTO vehicleNotificationResponseTO = vehicleServicesRequestESI.sendNotificationRequest(vehicleNotificationRequestTO);

        return vehicleNotificationMapper.mapResponse(vehicleNotificationResponseTO);
    }
}
