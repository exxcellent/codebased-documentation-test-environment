package com.example.bmw.vehicle.information.backendprocessing.business.store.boundary;

import java.util.Set;
import com.example.bmw.vehicle.information.backendprocessing.business.store.entity.VehicleInformationBE;

public interface VehicleInformationStoreICI {
    VehicleInformationBE getVehicleInformation(int identifier);
    VehicleInformationBE createVehicleInformation(String vehicleName);
    VehicleInformationBE updateVehicleInformation(VehicleInformationBE newVehicleInformationBE);
    void deleteVehicleInformation(int identifier);

    Set<VehicleInformationBE> findAll();
}
