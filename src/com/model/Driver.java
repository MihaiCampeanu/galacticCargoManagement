package com.model;

import java.util.List;

public class Driver {
    private Long id;
    private String name;
    private List<String> type;

    public Driver(Long id, String name, List<String> type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Driver(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTypeList() {
        return type;
    }

    public void setTypeList(List<String> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name +
                " can fly ships with type " + type  ;
    }
}
