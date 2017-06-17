package de.diedavids.cuba.healthcheck.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Table(name = "DDCHC_HEALTH_CHECK_RUN")
@Entity(name = "ddchc$HealthCheckRun")
public class HealthCheckRun extends StandardEntity {
    private static final long serialVersionUID = 8485311513349157688L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXECUTED_AT", nullable = false)
    protected Date executedAt;

    @Column(name = "RESULT_", nullable = false)
    protected String result;

    @Column(name = "SUMMARY")
    protected String summary;

    @Column(name = "DETAILED_MESSAGE", length = 400)
    protected String detailedMessage;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "healthCheckRun")
    protected List<HealthCheckRunResult> results;

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }


    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }



    public void setExecutedAt(Date executedAt) {
        this.executedAt = executedAt;
    }

    public Date getExecutedAt() {
        return executedAt;
    }


    public void setResults(List<HealthCheckRunResult> results) {
        this.results = results;
    }

    public List<HealthCheckRunResult> getResults() {
        return results;
    }


    public void setResult(HealthCheckResultType result) {
        this.result = result == null ? null : result.getId();
    }

    public HealthCheckResultType getResult() {
        return result == null ? null : HealthCheckResultType.fromId(result);
    }



}