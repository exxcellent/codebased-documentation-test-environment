package com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.boundary;

import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.types.VehicleNotificationReportTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.types.VehicleNotificationTO;


public interface VehicleNotificationESI {
    VehicleNotificationReportTO sendNotification(VehicleNotificationTO vehicleNotificationTO);
}
