package com.example.xinghua1.xinghua1__fueltrack;

import android.animation.FloatArrayEvaluator;

import java.util.Date;

/**
 * Created by xinghua1 on 1/22/16.
 */
public class Initialization {
    private Date DateText;
    private String StationText;
    private float OdometerText;
    private String FuelgText;
    private float FuelaText;
    private float FueluText;

    private float Cost;


    // constructor to do error handling
    public Initialization(Date dateText,String stationText, String odometerText, String fuelgText,String fuelaText ,String fueluText) {

        if (dateText == null) {
            this.DateText = null;
        }
        else{this.DateText = dateText;}

        if (stationText == null) {
            this.StationText = null;
        }
        else{this.StationText = stationText;}
        try{
          this.OdometerText = Float.valueOf(odometerText);
        }catch (Exception e) {
            this.OdometerText = 0;
        }
        if (stationText == null) {
            this.FuelgText = null;
        }
        else{this.FuelgText = stationText;}
        try{
            this.FueluText = Float.valueOf(fueluText);
        }catch (Exception e) {
            this.FueluText = 0;
        }
        try{
            this.FuelaText = Float.valueOf(fuelaText);
        }catch (Exception e) {
            this.FuelaText = 0;
        }
        this.Cost = FuelaText * FueluText / 100;

    }
   // settters and getters
    public Date getDate() {
        return this.DateText;
    }
    public void setDate(Date dateText) {
        this.DateText = dateText;
    }

    public String getStation() {
        return this.StationText;
    }

    public void setStation(String stationText) {
        this.StationText = stationText;
    }

    public String getOdometer() {
        return String.format("%.1f",this.OdometerText);
    }

    public void setOdometer(float odometer) {
        this.OdometerText = odometer;
    }

    public String getFuelGrade() {
        return this.FuelgText;
    }

    public void setFuelGrade(String fuelgText) {
        this.FuelgText = fuelgText;
    }

    public String getFuelAmount() {
        return String.format("%.3f",this.FuelaText);
    }

    public void setFuelAmount(float fuelaText) {
        this.FuelaText = fuelaText;
    }

    public String getFuelUnitCost() {
        return String.format("%.1f",this.FueluText);
    }

    public void setFuelUnitCost(float fueluText) {
        this.FueluText = fueluText;
    }

    public String getFuelCost() {
        return String.format("%.2f",this.Cost);
    }


   // display format
    @Override
    public String toString(){
        return "Date: " + DateText+ " | Station: " + StationText +
                " | OdometerText: " + String.format("%.1f Km", OdometerText) + " | Fuel grade: " + FuelgText +" | Fuel amount: " +
                String.format("%.3f L",FuelaText) + " | Fuel unit cost " + String.format("%.1f cents/L",FueluText) +" | Fuel Cost "
                + String.format("%.2f dollars",Cost);
    }
}
