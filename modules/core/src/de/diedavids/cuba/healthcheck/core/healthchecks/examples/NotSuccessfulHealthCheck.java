package de.diedavids.cuba.healthcheck.core.healthchecks.examples;

import de.diedavids.cuba.healthcheck.core.healthchecks.DefaultHealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail;
import org.springframework.stereotype.Component;

@Component
public class NotSuccessfulHealthCheck extends DefaultHealthCheck {

    @Override
    public HealthCheckReportDetail check() {
        throw new RuntimeException("that didn't work out");
    }

    @Override
    protected String getConfigurationCode() {
        return "NotSuccessfulHealthCheck";
    }
}
