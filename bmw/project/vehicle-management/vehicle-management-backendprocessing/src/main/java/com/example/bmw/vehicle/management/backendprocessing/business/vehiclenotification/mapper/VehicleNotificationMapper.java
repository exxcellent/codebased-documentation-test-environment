package com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.mapper;

import java.time.format.DateTimeFormatter;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationTO;

public class VehicleNotificationMapper {
    public VehicleNotificationTO mapResponse(VehicleNotificationResponseTO vehicleNotificationResponseTO) {
        VehicleNotificationTO vehicleNotificationTO = new VehicleNotificationTO();

        vehicleNotificationTO.setVehicleId(vehicleNotificationResponseTO.getVehicleIdentification());
        vehicleNotificationTO.setVehicleName(vehicleNotificationResponseTO.getVehicleName());
        vehicleNotificationTO.setMessage(vehicleNotificationResponseTO.getMessage());
        vehicleNotificationTO.setTimestamp(vehicleNotificationResponseTO.getNotificationSendTime().format(
                DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        return vehicleNotificationTO;
    }
}
