package com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.boundary;

import java.util.Set;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.types.VehicleInformationTO;

public interface VehicleManagementBCI {
    VehicleInformationTO getVehicleInformation(int id);

    VehicleInformationTO createVehicleInformation(String name);

    VehicleInformationTO updateVehicleInformation(VehicleInformationTO vehicleInformationTO);

    void deleteVehicleInformation(int vehicleId);

    Set<VehicleInformationTO> getAllVehicleInformation();
}
