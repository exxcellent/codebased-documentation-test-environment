package com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.control;

import javax.inject.Inject;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.mapper.VehicleInformationMapper;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.boundary.VehicleNotificationESI;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.types.VehicleNotificationReportTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.types.VehicleNotificationTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehicleinformationrequest.boundary.VehicleInformationRequestESI;
import com.example.bmw.vehicle.services.backendprocessing.business.vehicleinformationrequest.types.VehicleInformationResponseTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.types.VehicleNotificationRequestTO;

public class VehicleNotificationHandlerBA {

    @Inject
    private VehicleInformationRequestESI vehicleInformationRequestESI;

    @Inject
    private VehicleNotificationESI vehicleNotificationESI;

    @Inject
    private VehicleInformationMapper vehicleInformationMapper;

    public VehicleNotificationResponseTO sendNotification(VehicleNotificationRequestTO vehicleNotificationRequestTO) {

        // get vehicle information
        VehicleInformationResponseTO vehicleInformationResponseTO = vehicleInformationRequestESI.requestVehicleInformation(vehicleNotificationRequestTO.getVehicleId());

        // send notification
        VehicleNotificationTO vehicleNotificationTO = new VehicleNotificationTO();

        vehicleNotificationTO.setVehicleId(vehicleInformationResponseTO.getVehicleId());
        vehicleNotificationTO.setName(vehicleInformationResponseTO.getName());
        vehicleNotificationTO.setMessage(vehicleNotificationRequestTO.getMessage());


        VehicleNotificationReportTO vehicleNotificationReportTO = vehicleNotificationESI.sendNotification(vehicleNotificationTO);

        // create response
        return vehicleInformationMapper.mapResponse(vehicleInformationResponseTO, vehicleNotificationRequestTO, vehicleNotificationReportTO);
    }
}
