package de.diedavids.cuba.healthcheck.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Lob;

@NamePattern("%s (%s)|name,result")
@Table(name = "DDCHC_HEALTH_CHECK_REPORT_DETAIL")
@Entity(name = "ddchc$HealthCheckReportDetail")
public class HealthCheckReportDetail extends StandardEntity {
    private static final long serialVersionUID = 4988447995454061628L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "RESULT_", nullable = false)
    protected String result;

    @Column(name = "CATEGORY")
    protected String category;

    @Column(name = "MESSAGE")
    protected String message;

    @Lob
    @Column(name = "DETAILED_MESSAGE")
    protected String detailedMessage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "HEALTH_CHECK_RUN_ID")
    protected HealthCheckReport healthCheckReport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CONFIGURATION_ID")
    protected HealthCheckConfiguration configuration;

    public void setConfiguration(HealthCheckConfiguration configuration) {
        this.configuration = configuration;
    }

    public HealthCheckConfiguration getConfiguration() {
        return configuration;
    }


    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }


    public void setHealthCheckReport(HealthCheckReport healthCheckReport) {
        this.healthCheckReport = healthCheckReport;
    }

    public HealthCheckReport getHealthCheckReport() {
        return healthCheckReport;
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


}