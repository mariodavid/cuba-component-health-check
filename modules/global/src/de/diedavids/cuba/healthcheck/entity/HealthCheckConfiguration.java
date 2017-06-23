package de.diedavids.cuba.healthcheck.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@DiscriminatorValue("GENERAL")
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamePattern("%s (%s)|name,active")
@Table(name = "DDCHC_HEALTH_CHECK_CONFIGURATION")
@Entity(name = "ddchc$HealthCheckConfiguration")
public class HealthCheckConfiguration extends StandardEntity {
    private static final long serialVersionUID = 5113940016811422152L;

    @NotNull
    @Column(name = "TYPE_", nullable = false)
    protected String type;

    @Column(name = "ACTIVE")
    protected Boolean active;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "CODE")
    protected String code;

    @Column(name = "DESCRIPTION", length = 4000)
    protected String description;

    @Column(name = "SOLUTION_INFORMATION", length = 4000)
    protected String solutionInformation;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "clear"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    protected HealthCheckCategory category;

    public void setCategory(HealthCheckCategory category) {
        this.category = category;
    }

    public HealthCheckCategory getCategory() {
        return category;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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


}