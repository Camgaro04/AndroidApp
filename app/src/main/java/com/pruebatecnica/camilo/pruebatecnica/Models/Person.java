package com.pruebatecnica.camilo.pruebatecnica.Models;

public class Person {

    private String names;
    private String lastName;
    private String bornDate;
    private String identification;
    private String profession;
    private boolean isMarried;
    private double monthBalance;
    private Vehicle actualVehicle;
    private String actual_vehicle_plate;
    public Person(){

    }


    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public double getMonthBalance() {
        return monthBalance;
    }

    public void setMonthBalance(double monthBalance) {
        this.monthBalance = monthBalance;
    }

    public Vehicle getActualVehicle() {
        return actualVehicle;
    }

    public void setActualVehicle(Vehicle actualVehicle) {
        this.actualVehicle = actualVehicle;
    }

    public String getActual_vehicle_plate() {
        return actual_vehicle_plate;
    }

    public void setActual_vehicle_plate(String actual_vehicle_plate) {
        this.actual_vehicle_plate = actual_vehicle_plate;
    }

    @Override
    public String toString() {
        return "{nombre:"+names+", Apellido:"+lastName+", fecha de nacimiento:"+bornDate+" }";
    }
}
