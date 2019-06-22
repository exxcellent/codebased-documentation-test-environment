package com.example.bmw.vehicle.information.backendprocessing.business.vehicleinformation.boundary;

import java.util.Set;
import com.example.bmw.vehicle.information.backendprocessing.business.vehicleinformation.types.VehicleTO;

public interface VehicleInformationBCI {
    VehicleTO getVehicleInformation(int id);

    VehicleTO createVehicleInformation(String name);

    VehicleTO updateVehicleInformation(VehicleTO newVehicleTO);

    void deleteVehicleInformation(int vehicleId);

    Set<VehicleTO> getAllVehicleInformation();
}
