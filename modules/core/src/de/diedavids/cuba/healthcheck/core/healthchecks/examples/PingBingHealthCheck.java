package de.diedavids.cuba.healthcheck.core.healthchecks.examples;

import de.diedavids.cuba.healthcheck.HealthCheckCategories;
import de.diedavids.cuba.healthcheck.core.healthchecks.ShellExecutionHealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail;
import org.springframework.stereotype.Component;

@Component
public class PingBingHealthCheck extends ShellExecutionHealthCheck {
    @Override
    public String getCategory() {
        return HealthCheckCategories.COMMUNICATION;
    }

    @Override
    protected String getShellCommand() {
        return "ping -c 4 www.bing.com";
    }

    @Override
    protected HealthCheckReportDetail handleSuccessfulExecution(Process shellCommandProcess) {
        return success("ping bing works");
    }

    @Override
    protected HealthCheckReportDetail handleErrorExecution(Process shellCommandProcess) {
        return warning("Could not ping bing, but doesn't really matter...");
    }

}
