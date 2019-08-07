package com.model;

public class Planet {
    private Integer id;
    private String name;
    private double distance;

    public Planet(Integer id, String name, Integer distance) {
        this.id = id;
        this.name = name;
        this.distance = distance;
    }

    public Planet(){}

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

    public String getHello()
    {
        return "Hello world!";
    }
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return  "For reaching " + name + " you need to travel " +
                 distance + " km.";
    }
}
