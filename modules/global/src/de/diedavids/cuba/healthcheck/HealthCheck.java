package de.diedavids.cuba.healthcheck;

import de.diedavids.cuba.healthcheck.entity.HealthCheckConfiguration;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail;

/**
 * Interface that defines a class that represents a single healt check
 * that gets executed during a run of the health check system report
 *
 * check defines the check that has be be executed. A HealthCheckReportDetail instance
 * has to be given back as a result to represent the outcome of the health check.
 */
public interface HealthCheck {

    /**
     * executes the check of the HealthCheck instance
     * @return health check run result that indicates the (un-)successful execution
     */
    HealthCheckReportDetail check();

    /**
     * defines the category of the health check
     * @return the category
     */
    String getCategory();

    HealthCheckConfiguration getConfiguration();

}
