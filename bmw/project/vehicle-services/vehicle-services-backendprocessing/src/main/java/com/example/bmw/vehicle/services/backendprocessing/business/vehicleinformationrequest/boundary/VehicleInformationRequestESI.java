package com.example.bmw.vehicle.services.backendprocessing.business.vehicleinformationrequest.boundary;

import com.example.bmw.vehicle.services.backendprocessing.business.vehicleinformationrequest.types.VehicleInformationResponseTO;


public interface VehicleInformationRequestESI {
    VehicleInformationResponseTO requestVehicleInformation(int vehicleId);
}
