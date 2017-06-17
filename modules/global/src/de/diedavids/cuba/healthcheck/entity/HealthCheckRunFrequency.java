package de.diedavids.cuba.healthcheck.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum HealthCheckRunFrequency implements EnumClass<Integer> {

    HIGH(2),
    MEDIUM(1),
    LOW(0);

    private Integer id;

    HealthCheckRunFrequency(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static HealthCheckRunFrequency fromId(Integer id) {
        for (HealthCheckRunFrequency at : HealthCheckRunFrequency.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}