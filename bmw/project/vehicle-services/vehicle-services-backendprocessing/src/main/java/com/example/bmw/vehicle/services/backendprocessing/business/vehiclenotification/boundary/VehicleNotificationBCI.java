package com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.boundary;

import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.types.VehicleNotificationRequestTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;

public interface VehicleNotificationBCI {
    VehicleNotificationResponseTO sendVehicleNotification(VehicleNotificationRequestTO vehicleNotificationRequestTO);
}
