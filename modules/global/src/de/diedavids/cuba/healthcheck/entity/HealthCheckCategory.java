package de.diedavids.cuba.healthcheck.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|name")
@Table(name = "DDCHC_HEALTH_CHECK_CATEGORY")
@Entity(name = "ddchc$HealthCheckCategory")
public class HealthCheckCategory extends StandardEntity {
    private static final long serialVersionUID = -8092554264971374961L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}