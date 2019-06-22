package com.example.bmw.vehicle.management.backendprocessing.business.vehicleinformationrequest.boundary;

import java.util.Set;
import com.example.bmw.vehicle.management.backendprocessing.business.vehicleinformationrequest.types.VehicleInformationResponseTO;


public interface VehicleInformationRequestESI {
    VehicleInformationResponseTO requestVehicleInformation(int vehicleId);

    VehicleInformationResponseTO createVehicleInformation(String name);

    VehicleInformationResponseTO updateVehicleInformation(VehicleInformationResponseTO newVehicleInformationResponseTO);

    void deleteVehicleInformation(int vehicleId);

    VehicleInformationResponseTO[] requestAllVehicleInformation();
}
