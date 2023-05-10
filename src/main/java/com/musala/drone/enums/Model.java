package com.musala.drone.enums;

public enum Model {
    LIGHTWEIGHT("for light weight loads"),
    MIDDLEWEIGHT("for medium weight loads"),
    CRUISERWEIGHT("for moderate heavy weight loads"),
    HEAVYWEIGHT("for heavy weight loads");

    private String description;

    private Model(String description){
        this.description=description;
    }

    public String getDescription() {
        return description;
    }
}
