package com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.boundary;

import java.util.Set;
import javax.inject.Inject;
import com.example.bmw.common.validation.Preconditions;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.control.VehicleManagementBA;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.types.VehicleInformationTO;

public class VehicleManagementBF implements VehicleManagementBCI {

    @Inject
    private VehicleManagementBA vehicleManagementBA;

    @Override
    public VehicleInformationTO getVehicleInformation(int vehicleId) {
        Preconditions.checkArgument(vehicleId > 0, "ID must not be null");

        return vehicleManagementBA.getVehicleInformation(vehicleId);
    }


    @Override
    public VehicleInformationTO createVehicleInformation(String name) {
        Preconditions.checkNotNullOrEmpty(name, "Name must not be null");

        return vehicleManagementBA.createVehicleInformation(name);
    }


    @Override
    public VehicleInformationTO updateVehicleInformation(VehicleInformationTO newVehicleInformationTO) {
        Preconditions.checkNotNull(newVehicleInformationTO, "VehicleInformationTO must not be null");

        return vehicleManagementBA.updateVehicleInformation(newVehicleInformationTO);
    }


    @Override
    public void deleteVehicleInformation(int vehicleId) {
        Preconditions.checkArgument(vehicleId > 0, "ID must not be null");

        vehicleManagementBA.deleteVehicleInformation(vehicleId);
    }


    @Override
    public Set<VehicleInformationTO> getAllVehicleInformation() {
        return vehicleManagementBA.findAll();
    }
}
