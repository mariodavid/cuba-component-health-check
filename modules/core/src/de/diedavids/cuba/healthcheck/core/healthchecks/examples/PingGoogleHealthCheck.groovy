package de.diedavids.cuba.healthcheck.core.healthchecks.examples

import de.diedavids.cuba.healthcheck.HealthCheckCategories
import de.diedavids.cuba.healthcheck.core.healthchecks.ShellExecutionHealthCheck
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunFrequency
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult
import org.springframework.stereotype.Component

@Component
class PingGoogleHealthCheck extends ShellExecutionHealthCheck {


    @Override
    String getCategory() {
        HealthCheckCategories.COMMUNICATION
    }

    @Override
    HealthCheckRunFrequency getFrequency() {
        return HealthCheckRunFrequency.LOW
    }
    @Override
    protected String getShellCommand() {
        "ping -c 4 www.google.com"
    }

    @Override
    protected HealthCheckRunResult handleErrorExecution(Process shellCommandProcess) {
        error("Couldn't ping www.google.com")
    }

    @Override
    protected HealthCheckRunResult handleSuccessfulExecution(Process shellCommandProcess) {
        success("ping google successful")
    }
}
