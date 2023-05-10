package com.musala.drone.enums;

public enum State {

    IDLE("available for transportation"),
    LOADING("fleet loading"),
    LOADED("fleet ready for takeoff"),
    DELIVERING("fleet in transit"),
    DELIVERED("load delivered"),
    RETURNING("fleet returning");


    private String description;

    private State(String description){
        this.description=description;
    }

    public String getSate() {
        return description;
    }


}
