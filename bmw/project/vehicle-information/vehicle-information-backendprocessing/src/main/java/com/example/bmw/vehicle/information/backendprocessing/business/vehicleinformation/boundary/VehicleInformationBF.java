package com.example.bmw.vehicle.information.backendprocessing.business.vehicleinformation.boundary;

import java.util.Set;
import javax.inject.Inject;
import com.example.bmw.common.validation.Preconditions;
import com.example.bmw.vehicle.information.backendprocessing.business.vehicleinformation.control.VehicleInformationBA;
import com.example.bmw.vehicle.information.backendprocessing.business.vehicleinformation.types.VehicleTO;

public class VehicleInformationBF implements VehicleInformationBCI {

    @Inject
    private VehicleInformationBA vehicleInformationBA;

    @Override
    public VehicleTO getVehicleInformation(int vehicleId) {
        Preconditions.checkArgument(vehicleId > 0, "ID must not be null");

        return vehicleInformationBA.getVehicleInformation(vehicleId);
    }


    @Override
    public VehicleTO createVehicleInformation(String name) {
        Preconditions.checkNotNullOrEmpty(name, "Name must not be null");

        return vehicleInformationBA.createVehicleInformation(name);
    }


    @Override
    public VehicleTO updateVehicleInformation(VehicleTO newVehicleTO) {
        Preconditions.checkNotNull(newVehicleTO, "VehicleTO must not be null");

        return vehicleInformationBA.updateVehicleInformation(newVehicleTO);
    }


    @Override
    public void deleteVehicleInformation(int vehicleId) {
        Preconditions.checkArgument(vehicleId > 0, "ID must not be null");

        vehicleInformationBA.deleteVehicleInformation(vehicleId);
    }


    @Override
    public Set<VehicleTO> getAllVehicleInformation() {
        return vehicleInformationBA.findAll();
    }
}
