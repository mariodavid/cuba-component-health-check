package de.diedavids.cuba.healthcheck.core.healthchecks

import com.haulmont.cuba.core.global.Metadata
import de.diedavids.cuba.healthcheck.entity.HealtCheckReportDetailFactory;
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail
import org.springframework.stereotype.Component

import javax.inject.Inject;

@Component(HealtCheckReportDetailFactory.NAME)
class HealtCheckReportDetailFactoryBean implements HealtCheckReportDetailFactory {

    @Inject
    Metadata metadata;

    HealthCheckReportDetail success(String name, String category, String message, String detailedMessage = null) {
        createResult(HealthCheckResultType.SUCCESS, name, category, message, detailedMessage)
    }

    HealthCheckReportDetail createResult(HealthCheckResultType type, String name, String category, String message, String detailedMessage) {
        def result = metadata.create(HealthCheckReportDetail)
        result.result = type
        result.message = message
        result.detailedMessage = detailedMessage
        result.category = category
        result.name = name
        result
    }

    HealthCheckReportDetail error(String name, String category, String message, String detailedMessage = null) {
        createResult(HealthCheckResultType.ERROR, name, category, message, detailedMessage)
    }

    HealthCheckReportDetail warning(String name, String category, String message, String detailedMessage = null) {
        createResult(HealthCheckResultType.WARNING, name, category, message, detailedMessage)
    }
}
