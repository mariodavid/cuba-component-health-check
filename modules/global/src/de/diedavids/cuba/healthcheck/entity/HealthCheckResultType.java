package de.diedavids.cuba.healthcheck.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum HealthCheckResultType implements EnumClass<String> {

    ERROR("ERROR", "font-icon:EXCLAMATION", "health-check-table-row-error", "health-check-field-error"),
    WARNING("WARNING", "font-icon:INFO", "health-check-table-row-warning", "health-check-field-warning"),
    SUCCESS("SUCCESS", "font-icon:CHECK", "health-check-table-row-success", "health-check-field-success");

    private String id;
    private String icon;
    private String fieldStyleName;
    private String columnStyleName;

    HealthCheckResultType(String value, String icon, String columnStyleName, String fieldStyleName ) {
        this.id = value;
        this.icon = icon;
        this.fieldStyleName = fieldStyleName;
        this.columnStyleName = columnStyleName;
    }

    public String getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }
    public String getFieldStyleName() {
        return fieldStyleName;
    }
    public String getColumnStyleName() {
        return columnStyleName;
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