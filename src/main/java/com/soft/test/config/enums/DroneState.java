package com.soft.test.config.enums;

public enum DroneState {

    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    private final String state;

    /**
     * @param state
     */
    private DroneState(String state) {
        this.state = state;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }
}
