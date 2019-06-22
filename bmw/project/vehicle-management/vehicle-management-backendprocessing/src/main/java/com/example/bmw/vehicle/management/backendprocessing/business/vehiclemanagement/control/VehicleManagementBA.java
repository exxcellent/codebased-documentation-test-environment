package com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.control;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import com.example.bmw.vehicle.management.backendprocessing.business.vehicleinformationrequest.boundary.VehicleInformationRequestESI;
import com.example.bmw.vehicle.management.backendprocessing.business.vehicleinformationrequest.types.VehicleInformationResponseTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.mapper.VehicleManagementMapper;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.types.VehicleInformationTO;


public class VehicleManagementBA {

    @Inject
    private VehicleInformationRequestESI vehicleInformationRequestESI;

    @Inject
    private VehicleManagementMapper vehicleManagementMapper;

    public VehicleInformationTO getVehicleInformation(int id) {
        VehicleInformationResponseTO vehicleInformationResponseTO = vehicleInformationRequestESI.requestVehicleInformation(id);

        return vehicleManagementMapper.mapResponse(vehicleInformationResponseTO);
    }


    public VehicleInformationTO createVehicleInformation(String name) {
        VehicleInformationResponseTO vehicleInformationResponseTO = vehicleInformationRequestESI.createVehicleInformation(name);

        return vehicleManagementMapper.mapResponse(vehicleInformationResponseTO);
    }


    public VehicleInformationTO updateVehicleInformation(VehicleInformationTO newVehicleInformationTO) {

        VehicleInformationResponseTO newVehicleInformationResponseTO = vehicleManagementMapper.toTO(newVehicleInformationTO);

        VehicleInformationResponseTO vehicleInformationResponseTO = vehicleInformationRequestESI.updateVehicleInformation(newVehicleInformationResponseTO);


        return vehicleManagementMapper.mapResponse(vehicleInformationResponseTO);
    }


    public void deleteVehicleInformation(int vehicleId) {
        vehicleInformationRequestESI.deleteVehicleInformation(vehicleId);
    }


    public Set<VehicleInformationTO> findAll() {
        VehicleInformationResponseTO[] allEntities = vehicleInformationRequestESI.requestAllVehicleInformation();

        return Arrays.stream(allEntities)
                .filter(Objects::nonNull)
                .map(vehicleTO -> vehicleManagementMapper.mapResponse(vehicleTO))
                .collect(Collectors.toSet());
    }
}
