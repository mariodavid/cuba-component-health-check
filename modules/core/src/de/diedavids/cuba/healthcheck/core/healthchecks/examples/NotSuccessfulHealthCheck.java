package de.diedavids.cuba.healthcheck.core.healthchecks.examples;

import de.diedavids.cuba.healthcheck.HealthCheckCategories;
import de.diedavids.cuba.healthcheck.core.healthchecks.AbstractHealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult;
import org.springframework.stereotype.Component;

@Component
public class NotSuccessfulHealthCheck extends AbstractHealthCheck {
    @Override
    public String getCategory() {
        return HealthCheckCategories.COMMUNICATION;
    }


    @Override
    public HealthCheckRunResult check() {
        throw new RuntimeException("that was nothing");
    }

}
