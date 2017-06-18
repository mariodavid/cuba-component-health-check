package de.diedavids.cuba.healthcheck.service;


import de.diedavids.cuba.healthcheck.entity.HealthCheckRun;

public interface HealthCheckService {
    String NAME = "ddchc_HealthCheckService";

    HealthCheckRun runHealthChecks();

    HealthCheckRun getLatestHealthCheck();
}