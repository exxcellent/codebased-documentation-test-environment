package com.example.bmw.vehicle.information.backendprocessing.business.store.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import com.example.bmw.common.validation.Preconditions;

@ApplicationScoped
public class VehicleInformationStoreEM {
    private static final Logger LOG = Logger.getLogger( VehicleInformationStoreEM.class.getName() );

    private static AtomicInteger sequence = new AtomicInteger(1);
    private static final Map<Integer, VehicleInformationBE> DATABASE = new HashMap<> ();

    public VehicleInformationBE findById(int id) {
        LOG.info(String.format("Find vehicle information entity mock for ID: %s", id));

      VehicleInformationBE vehicleInformationBE = DATABASE.get(id);

      Preconditions.checkNotNull(vehicleInformationBE, "No vehicle information found for ID: " + id);

      return vehicleInformationBE;
  }

    public VehicleInformationBE create(String vehicleName) {
        Preconditions.checkNotNullOrEmpty(vehicleName, "VehicleName must not be null");

        LOG.info(String.format("Create vehicle information entity mock with name: %s", vehicleName));


        VehicleInformationBE vehicleInformationBE = new VehicleInformationBE();

        vehicleInformationBE.setId(sequence.getAndIncrement());
        vehicleInformationBE.setName(vehicleName);

        DATABASE.put(vehicleInformationBE.getId(), vehicleInformationBE);

        return vehicleInformationBE;
    }

    public VehicleInformationBE update(VehicleInformationBE updatedVehicleInformationBE) {
        Preconditions.checkNotNull(updatedVehicleInformationBE, "VehicleInformationBE must not be null");

        LOG.info(String.format("Update vehicle information entity mock: %s", updatedVehicleInformationBE.toString()));

        DATABASE.put(updatedVehicleInformationBE.getId(), updatedVehicleInformationBE);

        return updatedVehicleInformationBE;
    }

    public void delete(int vehicleId) {
        Preconditions.checkArgument(vehicleId > 0, "vehicleId must not be null");

        LOG.info(String.format("Delete vehicle information entity mock with ID: %s", vehicleId));

        DATABASE.remove(vehicleId);
    }


    public Set<VehicleInformationBE> findAll() {
        return new HashSet<>(DATABASE.values());
    }
}
