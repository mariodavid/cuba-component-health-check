package de.diedavids.cuba.healthcheck.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum HealthCheckResultType implements EnumClass<String> {

    ERROR("ERROR", "font-icon:EXCLAMATION"),
    WARNING("WARNING", "font-icon:INFO"),
    SUCCESS("SUCCESS", "font-icon:CHECK");

    private String id;
    private String icon;

    HealthCheckResultType(String value, String icon) {
        this.id = value;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    @Nullable
    public static HealthCheckResultType fromId(String id) {
        for (HealthCheckResultType at : HealthCheckResultType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}