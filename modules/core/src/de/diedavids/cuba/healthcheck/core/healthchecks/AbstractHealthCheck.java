package de.diedavids.cuba.healthcheck.core.healthchecks;

import de.diedavids.cuba.healthcheck.HealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealtCheckReportDetailFactory;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail;

import javax.inject.Inject;

public abstract class AbstractHealthCheck implements HealthCheck {

    @Inject
    HealtCheckReportDetailFactory resultFactory;


    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    protected HealthCheckReportDetail success(String message) {
        return resultFactory.success(getName(), getCategory(), message);
    }

    protected HealthCheckReportDetail success(String message, String detailedMessage) {
        return resultFactory.success(getName(), getCategory(), message, detailedMessage);
    }

    protected HealthCheckReportDetail warning(String message) {
        return resultFactory.warning(getName(), getCategory(), message);
    }

    protected HealthCheckReportDetail warning(String message, String detailedMessage) {
        return resultFactory.warning(getName(), getCategory(), message, detailedMessage);
    }

    protected HealthCheckReportDetail error(String message) {
        return resultFactory.error(getName(), getCategory(), message);
    }

    protected HealthCheckReportDetail error(String message, String detailedMessage) {
        return resultFactory.error(getName(), getCategory(), message, detailedMessage);
    }


}
