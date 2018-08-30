package com.pruebatecnica.camilo.pruebatecnica.Models;


public class Vehicle {

    private String vehicularPlate;
    private String brand;
    private String model;
    private String vehicleType;
    private int doorsNumber;


    public Vehicle(){
    }

    public Vehicle(String vehicularPlate,String brand , String model, String vehicleType,int doorsNumber){
        this.vehicularPlate = vehicularPlate;
        this.brand = brand;
        this.model = model;
        this.vehicleType = vehicleType;
        this.doorsNumber = doorsNumber;
    }

    public String getVehicularPlate() {
        return vehicularPlate;
    }

    public void setVehicularPlate(String vehicularPlate) {
        this.vehicularPlate = vehicularPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getDoorNumber() {
        return doorsNumber;
    }

    public void setDoorNumber(int doorNumber) {
        this.doorsNumber = doorNumber;
    }

    @Override
    public String toString() {
        return "{plate: "+vehicularPlate+", brand: "+brand+",model: "+model+", vehicleType: "+vehicleType+", doorsNumber: "+doorsNumber+" }";
    }
}
