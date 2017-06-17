package de.diedavids.cuba.healthcheck.core.healthchecks;

import de.diedavids.cuba.healthcheck.HealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult;
import de.diedavids.cuba.healthcheck.entity.HealtCheckResultFactory;

import javax.inject.Inject;

public abstract class AbstractHealthCheck implements HealthCheck {

    @Inject
    HealtCheckResultFactory resultFactory;


    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    protected HealthCheckRunResult success(String message) {
        return resultFactory.success(getName(), getCategory(), message);
    }

    protected HealthCheckRunResult success(String message, String detailedMessage) {
        return resultFactory.success(getName(), getCategory(), message, detailedMessage);
    }

    protected HealthCheckRunResult warning(String message) {
        return resultFactory.warning(getName(), getCategory(), message);
    }

    protected HealthCheckRunResult warning(String message, String detailedMessage) {
        return resultFactory.warning(getName(), getCategory(), message, detailedMessage);
    }

    protected HealthCheckRunResult error(String message) {
        return resultFactory.error(getName(), getCategory(), message);
    }

    protected HealthCheckRunResult error(String message, String detailedMessage) {
        return resultFactory.error(getName(), getCategory(), message, detailedMessage);
    }


}
