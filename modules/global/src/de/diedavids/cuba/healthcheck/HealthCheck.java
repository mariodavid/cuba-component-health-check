package de.diedavids.cuba.healthcheck;

import de.diedavids.cuba.healthcheck.entity.HealthCheckRunFrequency;
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult;

public interface HealthCheck {

    /**
     * executes the check of the HealthCheck instance
     * @return health check run result that indicates the (un-)successful execution
     */
    public HealthCheckRunResult check();


    /**
     * defines the category of the health check
     * @return the category
     */
    public String getCategory();

    /**
     * defines the name of the health check
     * @return the name
     */
    public String getName();

    /**
     * defines the frequency in that the health check should be executed periodically
     * @return the frequency
     */
    public HealthCheckRunFrequency getFrequency();
}
