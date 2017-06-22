package de.diedavids.cuba.healthcheck.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Lob;

@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@NamePattern("%s (%s)|healthCheckClass,active")
@Table(name = "DDCHC_HEALTH_CHECK_CONFIGURATION")
@Entity(name = "ddchc$HealthCheckConfiguration")
public class HealthCheckConfiguration extends StandardEntity {
    private static final long serialVersionUID = 5113940016811422152L;

    @NotNull
    @Column(name = "TYPE_", nullable = false)
    protected String type;

    @Column(name = "ACTIVE")
    protected Boolean active;

    @Column(name = "DESCRIPTION", length = 4000)
    protected String description;

    @Column(name = "SOLUTION_INFORMATION", length = 4000)
    protected String solutionInformation;

    @Column(name = "HEALTH_CHECK_CLASS")
    protected String healthCheckClass;

    @Lob
    @Column(name = "SCRIPT")
    protected String script;

    public void setScript(String script) {
        this.script = script;
    }

    public String getScript() {
        return script;
    }


    public void setType(HealthCheckType type) {
        this.type = type == null ? null : type.getId();
    }

    public HealthCheckType getType() {
        return type == null ? null : HealthCheckType.fromId(type);
    }


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