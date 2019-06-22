package com.example.bmw.vehicle.information.backendprocessing.business.vehicleinformation.control;

import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import com.example.bmw.vehicle.information.backendprocessing.business.store.boundary.VehicleInformationStoreICI;
import com.example.bmw.vehicle.information.backendprocessing.business.store.entity.VehicleInformationBE;
import com.example.bmw.vehicle.information.backendprocessing.business.vehicleinformation.mapper.VehicleServicesMapper;
import com.example.bmw.vehicle.information.backendprocessing.business.vehicleinformation.types.VehicleTO;


public class VehicleInformationBA {

    @Inject
    private VehicleInformationStoreICI vehicleInformationStoreICI;

    @Inject
    private VehicleServicesMapper vehicleInformationMapper;

    public VehicleTO getVehicleInformation(int id) {
        VehicleInformationBE vehicleInformationBE = vehicleInformationStoreICI.getVehicleInformation(id);
        return vehicleInformationMapper.mapEntity(vehicleInformationBE);
    }


    public VehicleTO createVehicleInformation(String name) {
        VehicleInformationBE vehicleInformationBE = vehicleInformationStoreICI.createVehicleInformation(name);
        return vehicleInformationMapper.mapEntity(vehicleInformationBE);
    }


    public VehicleTO updateVehicleInformation(VehicleTO newVehicleTO) {
        VehicleInformationBE newVehicleInformationBE = vehicleInformationMapper.toEntity(newVehicleTO);

        VehicleInformationBE vehicleInformationBE = vehicleInformationStoreICI.updateVehicleInformation(newVehicleInformationBE);
        return vehicleInformationMapper.mapEntity(vehicleInformationBE);
    }


    public void deleteVehicleInformation(int vehicleId) {
        vehicleInformationStoreICI.deleteVehicleInformation(vehicleId);
    }


    public Set<VehicleTO> findAll() {
        Set<VehicleInformationBE> allEntities = vehicleInformationStoreICI.findAll();

        return allEntities.stream()
                .map(vehicleInformationBE -> vehicleInformationMapper.mapEntity(vehicleInformationBE))
                .collect(Collectors.toSet());
    }
}
