package com.model;

public class TransportationInfo {
    private String driver_name;
    private Double cargo;
    private String planet_name;
    private Double time;
    private String ship_name;
    private Integer trips ;

    public TransportationInfo(String driver_name, Double cargo, String planet_name, Double time, String ship_name, Integer trips) {
        this.driver_name = driver_name;
        this.cargo = cargo;
        this.planet_name = planet_name;
        this.time = time;
        this.ship_name = ship_name;
        this.trips = trips;
    }

    
    public String getDriver_name() {
        return driver_name;
    }

    public Double getCargo() {
        return cargo;
    }

    public String getPlanet_name() {
        return planet_name;
    }

    public Double getTime() {
        return time;
    }

    public Time getTheTime(double time){
        double  fractional  = time % 1;
        double  whole = time - fractional;

        int hours = (int) whole;
        int minutes = (int)(fractional * 60);
        return new Time(hours, minutes);
    }

    public String getShip_name() {
        return ship_name;
    }

    public Integer getTrips() {
        return trips;
    }

    @Override
    public String toString() {
        return driver_name + " can transport "
                + cargo + " kg" +
                " to " + planet_name +
                " in " + this.getTheTime(time) +
                " with " + ship_name +
                " making " + trips + " trips.";
    }
}
