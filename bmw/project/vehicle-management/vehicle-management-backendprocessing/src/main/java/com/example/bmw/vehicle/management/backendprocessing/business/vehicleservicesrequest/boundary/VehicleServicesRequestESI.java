package com.example.bmw.vehicle.management.backendprocessing.business.vehicleservicesrequest.boundary;

import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationRequestTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;


public interface VehicleServicesRequestESI {
    VehicleNotificationResponseTO sendNotificationRequest(VehicleNotificationRequestTO vehicleNotificationRequestTO);
}
