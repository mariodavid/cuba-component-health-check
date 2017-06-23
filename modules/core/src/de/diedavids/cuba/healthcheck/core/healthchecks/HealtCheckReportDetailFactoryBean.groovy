package de.diedavids.cuba.healthcheck.core.healthchecks

import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import de.diedavids.cuba.healthcheck.data.SimpleDataLoader
import de.diedavids.cuba.healthcheck.entity.HealtCheckReportDetailFactory
import de.diedavids.cuba.healthcheck.entity.HealthCheckCategory
import de.diedavids.cuba.healthcheck.entity.HealthCheckConfiguration;
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail
import org.springframework.stereotype.Component

import javax.inject.Inject;

@Component(HealtCheckReportDetailFactory.NAME)
class HealtCheckReportDetailFactoryBean implements HealtCheckReportDetailFactory {

    @Inject
    Metadata metadata;

    @Inject
    SimpleDataLoader simpleDataLoader

    HealthCheckReportDetail success(HealthCheckConfiguration configuration, String message, String detailedMessage = null) {
        createResult(configuration, HealthCheckResultType.SUCCESS, message, detailedMessage)
    }

    HealthCheckReportDetail createResult(HealthCheckConfiguration configuration, HealthCheckResultType type, String message, String detailedMessage) {
        def result = metadata.create(HealthCheckReportDetail)
        result.configuration = configuration
        result.result = type
        result.message = message
        result.detailedMessage = detailedMessage
        result
    }

    HealthCheckReportDetail error(HealthCheckConfiguration configuration, String message, String detailedMessage = null) {
        createResult(configuration, HealthCheckResultType.ERROR, message, detailedMessage)
    }

    HealthCheckReportDetail warning(HealthCheckConfiguration configuration, String message, String detailedMessage = null) {
        createResult(configuration, HealthCheckResultType.WARNING, message, detailedMessage)
    }
}
