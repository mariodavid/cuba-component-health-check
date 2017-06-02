package de.diedavids.cuba.healthcheck.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s (%s)|name,result")
@Table(name = "DDCHC_HEALTH_CHECK_RUN_RESULT")
@Entity(name = "ddchc$HealthCheckRunResult")
public class HealthCheckRunResult extends StandardEntity {
    private static final long serialVersionUID = 4988447995454061628L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "RESULT_", nullable = false)
    protected String result;

    @Column(name = "SUMMARY")
    protected String summary;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "HEALTH_CHECK_RUN_ID")
    protected HealthCheckRun healthCheckRun;

    public void setHealthCheckRun(HealthCheckRun healthCheckRun) {
        this.healthCheckRun = healthCheckRun;
    }

    public HealthCheckRun getHealthCheckRun() {
        return healthCheckRun;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setResult(HealthCheckResultType result) {
        this.result = result == null ? null : result.getId();
    }

    public HealthCheckResultType getResult() {
        return result == null ? null : HealthCheckResultType.fromId(result);
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }


}