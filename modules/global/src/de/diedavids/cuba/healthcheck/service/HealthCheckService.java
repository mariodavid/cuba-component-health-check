package de.diedavids.cuba.healthcheck.service;


import de.diedavids.cuba.healthcheck.entity.HealthCheckReport;

public interface HealthCheckService {
    String NAME = "ddchc_HealthCheckService";

    HealthCheckReport runHealthChecks();

    HealthCheckReport getLatestHealthCheckReport();
}