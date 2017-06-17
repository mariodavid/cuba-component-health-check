package de.diedavids.cuba.healthcheck.entity;

public interface HealtCheckResultFactory {

    String NAME = "ddchc_HealthCheckResultFactory";

    HealthCheckRunResult success(String name, String category,String message);
    HealthCheckRunResult success(String name, String category,String message, String detailedMessage);

    HealthCheckRunResult warning(String name, String category,String message);
    HealthCheckRunResult warning(String name, String category,String message, String detailedMessage);

    HealthCheckRunResult error(String name, String category,String message);
    HealthCheckRunResult error(String name, String category,String message, String detailedMessage);


    HealthCheckRunResult createResult(HealthCheckResultType type,String name, String category, String message, String detailedMessage);

}
