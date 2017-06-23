package de.diedavids.cuba.healthcheck.core.healthchecks

import com.haulmont.cuba.core.global.Metadata
import de.diedavids.cuba.healthcheck.entity.HealtCheckReportDetailFactory
import de.diedavids.cuba.healthcheck.entity.HealthCheckConfiguration;
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail
import org.springframework.stereotype.Component

import javax.inject.Inject;

@Component(HealtCheckReportDetailFactory.NAME)
class HealtCheckReportDetailFactoryBean implements HealtCheckReportDetailFactory {

    @Inject
    Metadata metadata;

    HealthCheckReportDetail success(HealthCheckConfiguration configuration, String category, String message, String detailedMessage = null) {
        createResult(configuration, HealthCheckResultType.SUCCESS, category, message, detailedMessage)
    }

    HealthCheckReportDetail createResult(HealthCheckConfiguration configuration, HealthCheckResultType type, String category, String message, String detailedMessage) {
        def result = metadata.create(HealthCheckReportDetail)
        result.configuration = configuration
        result.result = type
        result.message = message
        result.detailedMessage = detailedMessage
        result.category = category
        result
    }

    HealthCheckReportDetail error(HealthCheckConfiguration configuration, String category, String message, String detailedMessage = null) {
        createResult(configuration, HealthCheckResultType.ERROR, category, message, detailedMessage)
    }

    HealthCheckReportDetail warning(HealthCheckConfiguration configuration, String category, String message, String detailedMessage = null) {
        createResult(configuration, HealthCheckResultType.WARNING, category, message, detailedMessage)
    }
}
