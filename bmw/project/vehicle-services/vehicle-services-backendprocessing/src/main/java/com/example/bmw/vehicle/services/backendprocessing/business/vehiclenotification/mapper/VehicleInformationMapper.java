package com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.mapper;

import com.example.bmw.vehicle.services.backendprocessing.business.vehicleinformationrequest.types.VehicleInformationResponseTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.types.VehicleNotificationReportTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.types.VehicleNotificationRequestTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;

public class VehicleInformationMapper {

    public VehicleNotificationResponseTO mapResponse(VehicleInformationResponseTO vehicleInformationResponseTO,
                                                     VehicleNotificationRequestTO vehicleNotificationRequestTO,
                                                     VehicleNotificationReportTO vehicleNotificationReportTO) {
        VehicleNotificationResponseTO mapped = new VehicleNotificationResponseTO();

        mapped.setVehicleName(vehicleInformationResponseTO.getName());
        mapped.setVehicleIdentification(vehicleInformationResponseTO.getVehicleId());

        mapped.setMessage(vehicleNotificationRequestTO.getMessage());

        mapped.setNotificationSend(vehicleNotificationReportTO.isNotificationSend());
        mapped.setNotificationSendTime(vehicleNotificationReportTO.getMessageSendAt());

        return mapped;
    }
}
