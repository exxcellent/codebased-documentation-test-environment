package com.example.bmw.vehicle.information.backendprocessing.business.vehicleservices.boundary;

import java.util.Set;
import com.example.bmw.vehicle.information.backendprocessing.business.vehicleservices.types.VehicleTO;

public interface VehicleInformationBCI {
    VehicleTO getVehicleInformation(int id);

    VehicleTO createVehicleInformation(String name);

    VehicleTO updateVehicleInformation(VehicleTO newVehicleTO);

    void deleteVehicleInformation(int vehicleId);

    Set<VehicleTO> getAllVehicleInformation();
}
