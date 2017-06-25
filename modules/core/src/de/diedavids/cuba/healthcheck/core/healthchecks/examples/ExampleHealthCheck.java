package de.diedavids.cuba.healthcheck.core.healthchecks.examples;

import de.diedavids.cuba.healthcheck.core.healthchecks.DefaultHealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail;
import org.springframework.stereotype.Component;

@Component
public class ExampleHealthCheck extends DefaultHealthCheck {

    @Override
    public HealthCheckReportDetail check() {
        return success("Everything worked out");
    }

    @Override
    protected String getConfigurationCode() {
        return "exampleHealCheckCode";
    }
}
