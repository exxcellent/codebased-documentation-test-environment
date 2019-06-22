package com.example.bmw.vehicle.information.backendprocessing.business.store.entity;

public class VehicleInformationBE {
    @Override
    public String toString() {
        return "VehicleInformationBE{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    private int id;
    private String name;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
