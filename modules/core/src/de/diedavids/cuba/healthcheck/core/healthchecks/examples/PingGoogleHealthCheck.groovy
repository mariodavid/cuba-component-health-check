package de.diedavids.cuba.healthcheck.core.healthchecks.examples

import de.diedavids.cuba.healthcheck.core.healthchecks.ShellExecutionHealthCheck
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail
import org.springframework.stereotype.Component

@Component
class PingGoogleHealthCheck extends ShellExecutionHealthCheck {

    @Override
    protected String getShellCommand() {
        'ping -c 1 www.google.com'
    }

    @Override
    protected HealthCheckReportDetail handleErrorExecution(Process shellCommandProcess) {
        error('Couldn\'t ping www.google.com')
    }

    @Override
    protected HealthCheckReportDetail handleSuccessfulExecution(Process shellCommandProcess) {
        success('ping google successful')
    }

    @Override
    protected String getConfigurationCode() {
        return 'PingGoogleHealthCheck'
    }
}
