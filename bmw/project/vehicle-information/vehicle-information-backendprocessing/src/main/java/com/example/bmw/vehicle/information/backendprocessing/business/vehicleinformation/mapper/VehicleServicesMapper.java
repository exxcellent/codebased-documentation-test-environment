package com.example.bmw.vehicle.information.backendprocessing.business.vehicleinformation.mapper;

import java.util.logging.Logger;
import com.example.bmw.vehicle.information.backendprocessing.business.store.entity.VehicleInformationBE;
import com.example.bmw.vehicle.information.backendprocessing.business.vehicleinformation.types.VehicleTO;

public class VehicleServicesMapper {
    private static final Logger LOG = Logger.getLogger( VehicleServicesMapper.class.getName() );

    public VehicleTO mapEntity(VehicleInformationBE vehicleInformationBE) {
        LOG.info(String.format("Mapping entity: %s", vehicleInformationBE.toString()));

        VehicleTO mapped = new VehicleTO();

        mapped.setName(vehicleInformationBE.getName());
        mapped.setVehicleId(vehicleInformationBE.getId());

        return mapped;
    }


    public VehicleInformationBE toEntity(VehicleTO newVehicleTO) {
        LOG.info(String.format("Mapping transfer object: %s", newVehicleTO.toString()));

        VehicleInformationBE vehicleInformationBE = new VehicleInformationBE();

        vehicleInformationBE.setId(newVehicleTO.getVehicleId());
        vehicleInformationBE.setName(newVehicleTO.getName());

        return vehicleInformationBE;
    }
}
