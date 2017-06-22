package de.diedavids.cuba.healthcheck.service;


import de.diedavids.cuba.healthcheck.HealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReport;

import java.util.Map;

public interface HealthCheckService {
    String NAME = "ddchc_HealthCheckService";

    HealthCheckReport runHealthChecks();

    HealthCheckReport getLatestHealthCheckReport();

    Map<String, HealthCheck> getProgrammaticallyDefinedChecks();
    Map<String, String> getProgrammaticallyDefinedChecksMap();
}