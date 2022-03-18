package com.example.bottleui;

public class Bottle {
    private String name;
    private String manufacturer;
    private double total_energy;
    private double bottlesize;
    private double prize;

    public Bottle(String pepsi_max, String pepsi, double v, double v1){
        name = pepsi_max;
        manufacturer = pepsi;
        bottlesize = v;
        prize = v1;
    }
    public String getName(){return name;}
    public String getManufacturer(){
        return manufacturer;
    }
    public double getEnergy(){
        return total_energy;
    }
    public double getPrize(){
        return prize;
    }
    public double getSize(){
        return bottlesize;
    }
}