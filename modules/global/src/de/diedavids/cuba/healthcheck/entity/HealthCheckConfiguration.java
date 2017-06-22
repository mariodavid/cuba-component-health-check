package de.diedavids.cuba.healthcheck.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s (%s)|healthCheckClass,active")
@Table(name = "DDCHC_HEALTH_CHECK_CONFIGURATION")
@Entity(name = "ddchc$HealthCheckConfiguration")
public class HealthCheckConfiguration extends StandardEntity {
    private static final long serialVersionUID = 5113940016811422152L;

    @Column(name = "ACTIVE")
    protected Boolean active;

    @Column(name = "HEALTH_CHECK_CLASS", nullable = false, length = 4000)
    protected String healthCheckClass;

    @Column(name = "DESCRIPTION", length = 4000)
    protected String description;

    @Column(name = "SOLUTION_INFORMATION", length = 4000)
    protected String solutionInformation;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSolutionInformation(String solutionInformation) {
        this.solutionInformation = solutionInformation;
    }

    public String getSolutionInformation() {
        return solutionInformation;
    }


    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setHealthCheckClass(String healthCheckClass) {
        this.healthCheckClass = healthCheckClass;
    }

    public String getHealthCheckClass() {
        return healthCheckClass;
    }


}