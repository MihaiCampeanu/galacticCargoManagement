package com.model;

public class Ship {
    private Integer id;
    private String name;
    private double speed;
    private String type;
    private Integer maxCargoWeight;

    public Ship(Integer id, String name, double speed, String type, Integer maxCargoWeight) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.type = type;
        this.maxCargoWeight = maxCargoWeight;
    }

    public Ship(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public void setMaxCargoWeight(Integer maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }

    @Override
    public String toString() {
        return  name + " is a ship with a " +
                "speed of " + speed + " km/h and it's catezorized as a "
                 + type + " type. The maximum amount of cargo it can carry is " + maxCargoWeight + " .";
    }
}
