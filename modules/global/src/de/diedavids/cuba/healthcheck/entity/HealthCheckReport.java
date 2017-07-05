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

@Table(name = "DDCHC_HC_REPORT")
@Entity(name = "ddchc$HealthCheckReport")
public class HealthCheckReport extends StandardEntity {
    private static final long serialVersionUID = 8485311513349157688L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXECUTED_AT", nullable = false)
    protected Date executedAt;

    @Column(name = "RESULT_", nullable = false)
    protected String result;

    @Column(name = "SUMMARY", length = 4000)
    protected String summary;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "healthCheckReport")
    protected List<HealthCheckReportDetail> checks;

    @Column(name = "INITIAL_CHECK")
    protected Boolean initialCheck;

    public void setInitialCheck(Boolean initialCheck) {
        this.initialCheck = initialCheck;
    }

    public Boolean getInitialCheck() {
        return initialCheck;
    }


    public void setChecks(List<HealthCheckReportDetail> checks) {
        this.checks = checks;
    }

    public List<HealthCheckReportDetail> getChecks() {
        return checks;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }




    public void setExecutedAt(Date executedAt) {
        this.executedAt = executedAt;
    }

    public Date getExecutedAt() {
        return executedAt;
    }



    public void setResult(HealthCheckResultType result) {
        this.result = result == null ? null : result.getId();
    }

    public HealthCheckResultType getResult() {
        return result == null ? null : HealthCheckResultType.fromId(result);
    }



}