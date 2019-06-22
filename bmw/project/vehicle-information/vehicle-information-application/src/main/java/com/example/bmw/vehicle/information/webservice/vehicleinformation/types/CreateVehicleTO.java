package com.example.bmw.vehicle.information.webservice.vehicleinformation.types;

public class CreateVehicleTO {
    private String name;


    @Override
    public String toString() {
        return "CreateVehicleTO{" +
                "name='" + name + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
