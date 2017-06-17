package de.diedavids.cuba.healthcheck.core.healthchecks.examples;

import de.diedavids.cuba.healthcheck.HealthCheckCategories;
import de.diedavids.cuba.healthcheck.core.healthchecks.ShellExecutionHealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunFrequency;
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult;
import org.springframework.stereotype.Component;

@Component
public class PingBingHealthCheck extends ShellExecutionHealthCheck {
    @Override
    public String getCategory() {
        return HealthCheckCategories.COMMUNICATION;
    }

    @Override
    public HealthCheckRunFrequency getFrequency() {
        return HealthCheckRunFrequency.HIGH;
    }

    @Override
    protected String getShellCommand() {
        return "ping -c 4 www.bing.com";
    }

    @Override
    protected HealthCheckRunResult handleSuccessfulExecution(Process shellCommandProcess) {
        return success("ping bing works");
    }

    @Override
    protected HealthCheckRunResult handleErrorExecution(Process shellCommandProcess) {
        return warning("Could not ping bing, but doesn't really matter...");
    }

}
