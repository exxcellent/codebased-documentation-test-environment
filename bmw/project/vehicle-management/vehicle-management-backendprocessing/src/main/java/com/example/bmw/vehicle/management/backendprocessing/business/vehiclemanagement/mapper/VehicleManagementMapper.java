package com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.mapper;

import java.util.logging.Logger;
import com.example.bmw.vehicle.management.backendprocessing.business.vehicleinformationrequest.types.VehicleInformationResponseTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.types.VehicleInformationTO;

public class VehicleManagementMapper {
    private static final Logger LOG = Logger.getLogger( VehicleManagementMapper.class.getName() );


    public VehicleInformationTO mapResponse(VehicleInformationResponseTO vehicleInformationResponseTO) {
        VehicleInformationTO vehicleInformationTO = new VehicleInformationTO();

        vehicleInformationTO.setVehicleId(vehicleInformationResponseTO.getVehicleId());
        vehicleInformationTO.setVehicleName(vehicleInformationResponseTO.getName());

        return vehicleInformationTO;
    }


    public VehicleInformationResponseTO toTO(VehicleInformationTO newVehicleInformationTO) {
        VehicleInformationResponseTO vehicleInformationResponseTO = new VehicleInformationResponseTO();

        vehicleInformationResponseTO.setVehicleId(newVehicleInformationTO.getVehicleId());
        vehicleInformationResponseTO.setName(newVehicleInformationTO.getVehicleName());

        return vehicleInformationResponseTO;
    }
}
