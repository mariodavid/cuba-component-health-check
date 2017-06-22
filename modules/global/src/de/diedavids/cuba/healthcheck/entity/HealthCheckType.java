package de.diedavids.cuba.healthcheck.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum HealthCheckType implements EnumClass<String> {

    SYSTEM("SYSTEM"),
    CUSTOM("CUSTOM");

    private String id;

    HealthCheckType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static HealthCheckType fromId(String id) {
        for (HealthCheckType at : HealthCheckType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}