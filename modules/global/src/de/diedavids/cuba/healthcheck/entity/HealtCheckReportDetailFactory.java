package de.diedavids.cuba.healthcheck.entity;

public interface HealtCheckReportDetailFactory {

    String NAME = "ddchc_HealthCheckResultFactory";

    HealthCheckReportDetail success(String name, String category, String message);
    HealthCheckReportDetail success(String name, String category, String message, String detailedMessage);

    HealthCheckReportDetail warning(String name, String category, String message);
    HealthCheckReportDetail warning(String name, String category, String message, String detailedMessage);

    HealthCheckReportDetail error(String name, String category, String message);
    HealthCheckReportDetail error(String name, String category, String message, String detailedMessage);


    HealthCheckReportDetail createResult(HealthCheckResultType type, String name, String category, String message, String detailedMessage);

}
