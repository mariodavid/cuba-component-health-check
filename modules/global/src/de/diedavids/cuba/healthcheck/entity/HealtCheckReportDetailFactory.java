package de.diedavids.cuba.healthcheck.entity;

public interface HealtCheckReportDetailFactory {

    String NAME = "ddchc_HealthCheckResultFactory";

    HealthCheckReportDetail success(HealthCheckConfiguration configuration, String category, String message);
    HealthCheckReportDetail success(HealthCheckConfiguration configuration, String category, String message, String detailedMessage);

    HealthCheckReportDetail warning(HealthCheckConfiguration configuration, String category, String message);
    HealthCheckReportDetail warning(HealthCheckConfiguration configuration, String category, String message, String detailedMessage);

    HealthCheckReportDetail error(HealthCheckConfiguration configuration, String category, String message);
    HealthCheckReportDetail error(HealthCheckConfiguration configuration, String category, String message, String detailedMessage);


    HealthCheckReportDetail createResult(HealthCheckConfiguration configuration, HealthCheckResultType type, String category, String message, String detailedMessage);

}
