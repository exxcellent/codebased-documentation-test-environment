package com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.boundary;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;
import java.util.logging.Logger;
import com.example.bmw.common.validation.BmwPreconditions;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.types.VehicleNotificationReportTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotificationsender.types.VehicleNotificationTO;

public class VehicleNotificationBF implements VehicleNotificationESI {
    private static final Logger LOG = Logger.getLogger( VehicleNotificationBF.class.getName() );

    @Override
    public VehicleNotificationReportTO sendNotification(VehicleNotificationTO vehicleNotificationTO) {
        BmwPreconditions.checkNotNull(vehicleNotificationTO, "VehicleNotificationTO must not be null");
        BmwPreconditions.checkNotNullOrEmpty(vehicleNotificationTO.getMessage(), "Message must not be null");
        BmwPreconditions.checkNotNullOrEmpty(vehicleNotificationTO.getName(), "Name must not be null");
        BmwPreconditions.checkArgument(vehicleNotificationTO.getVehicleId() > 0, "VehicleId must not be null");

        LOG.info(String.format("Simulate vehicle notification: %s", vehicleNotificationTO.toString()));

        VehicleNotificationReportTO vehicleNotificationReportTO = new VehicleNotificationReportTO();

        vehicleNotificationReportTO.setVehicleId(vehicleNotificationTO.getVehicleId());
        vehicleNotificationReportTO.setNotificationSend(true);
        vehicleNotificationReportTO.setMessageSendAt(OffsetDateTime.now(ZoneOffset.UTC));

        return vehicleNotificationReportTO;
    }
}
