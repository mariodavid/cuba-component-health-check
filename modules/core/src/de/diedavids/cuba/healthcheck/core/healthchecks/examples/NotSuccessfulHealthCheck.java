package de.diedavids.cuba.healthcheck.core.healthchecks.examples;

import de.diedavids.cuba.healthcheck.HealthCheckCategories;
import de.diedavids.cuba.healthcheck.core.healthchecks.DefaultHealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail;
import org.springframework.stereotype.Component;

@Component
public class NotSuccessfulHealthCheck extends DefaultHealthCheck {
    @Override
    public String getCategory() {
        return HealthCheckCategories.COMMUNICATION;
    }


    @Override
    public HealthCheckReportDetail check() {
        throw new RuntimeException("that was nothing");
    }

    @Override
    protected String getConfigurationCode() {
        return "NotSuccessfulHealthCheck";
    }
}
