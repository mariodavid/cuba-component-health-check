package de.diedavids.cuba.healthcheck.core.healthchecks.examples;

import de.diedavids.cuba.healthcheck.core.healthchecks.ShellExecutionHealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail;
import org.springframework.stereotype.Component;

@Component
public class PingBingHealthCheck extends ShellExecutionHealthCheck {

    @Override
    protected String getShellCommand() {
        return "ping -c 1 www.bing.com";
    }

    @Override
    protected HealthCheckReportDetail handleSuccessfulExecution(Process shellCommandProcess) {
        return success("ping bing works");
    }

    @Override
    protected HealthCheckReportDetail handleErrorExecution(Process shellCommandProcess) {
        return warning("Could not ping bing, but doesn't really matter...");
    }

    @Override
    protected String getConfigurationCode() {
        return "PingBingHealthCheck";
    }
}
