package com.vehisoc.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Type {

    CAR,
    MOPED,
    BIKE;

    @JsonCreator
    public static Type fromString(String value) {
        return value == null ? null : Type.valueOf(value.trim().toUpperCase());
    }
}
