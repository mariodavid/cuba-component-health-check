package de.diedavids.cuba.healthcheck.core.healthchecks;

import de.diedavids.cuba.healthcheck.HealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealtCheckReportDetailFactory;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail;

import javax.inject.Inject;

public abstract class AbstractHealthCheck implements HealthCheck {

    @Inject
    protected HealtCheckReportDetailFactory healtCheckReportDetailFactory;


    protected HealthCheckReportDetail success(String message) {
        return healtCheckReportDetailFactory.success(getConfiguration(), getCategory(), message);
    }

    protected HealthCheckReportDetail success(String message, String detailedMessage) {
        return healtCheckReportDetailFactory.success(getConfiguration(),getCategory(), message, detailedMessage);
    }

    protected HealthCheckReportDetail warning(String message) {
        return healtCheckReportDetailFactory.warning(getConfiguration(), getCategory(), message);
    }

    protected HealthCheckReportDetail warning(String message, String detailedMessage) {
        return healtCheckReportDetailFactory.warning(getConfiguration(), getCategory(), message, detailedMessage);
    }

    protected HealthCheckReportDetail error(String message) {
        return healtCheckReportDetailFactory.error(getConfiguration(), getCategory(), message);
    }

    protected HealthCheckReportDetail error(String message, String detailedMessage) {
        return healtCheckReportDetailFactory.error(getConfiguration(), getCategory(), message, detailedMessage);
    }

}
