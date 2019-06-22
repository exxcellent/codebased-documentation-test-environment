package com.example.bmw.vehicle.information.backendprocessing.business.store.control;

import java.util.Set;
import javax.inject.Inject;
import com.example.bmw.common.validation.Preconditions;
import com.example.bmw.vehicle.information.backendprocessing.business.store.boundary.VehicleInformationStoreICI;
import com.example.bmw.vehicle.information.backendprocessing.business.store.entity.VehicleInformationBE;
import com.example.bmw.vehicle.information.backendprocessing.business.store.entity.VehicleInformationStoreEM;

public class VehicleInformationProviderBA implements VehicleInformationStoreICI {

    @Inject
    private VehicleInformationStoreEM vehicleInformationStoreEM;

    @Override
    public VehicleInformationBE getVehicleInformation(int identifier) {
        return vehicleInformationStoreEM.findById(identifier);
    }


    @Override
    public VehicleInformationBE createVehicleInformation(String vehicleName) {
        return vehicleInformationStoreEM.create(vehicleName);
    }


    @Override
    public VehicleInformationBE updateVehicleInformation(VehicleInformationBE newVehicleInformationBE) {
        return vehicleInformationStoreEM.update(newVehicleInformationBE);
    }


    @Override
    public void deleteVehicleInformation(int identifier) {
        vehicleInformationStoreEM.delete(identifier);
    }


    @Override
    public Set<VehicleInformationBE> findAll() {
        return vehicleInformationStoreEM.findAll();
    }
}
