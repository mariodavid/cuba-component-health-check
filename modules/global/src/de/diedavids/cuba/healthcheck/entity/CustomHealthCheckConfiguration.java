package de.diedavids.cuba.healthcheck.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@DiscriminatorValue("CUSTOM")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "DDCHC_CUSTOM_HEALTH_CHECK_CONFIGURATION")
@Entity(name = "ddchc$CustomHealthCheckConfiguration")
public class CustomHealthCheckConfiguration extends HealthCheckConfiguration {
    private static final long serialVersionUID = 1177382026563700664L;


}