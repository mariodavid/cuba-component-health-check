package de.diedavids.cuba.healthcheck.core.healthchecks

import com.haulmont.cuba.core.global.Metadata
import de.diedavids.cuba.healthcheck.entity.HealtCheckResultFactory;
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType;
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult
import org.springframework.stereotype.Component

import javax.inject.Inject;

@Component(HealtCheckResultFactory.NAME)
class HealtCheckResultFactoryBean implements HealtCheckResultFactory {

    @Inject
    Metadata metadata;

    HealthCheckRunResult success(String name, String category, String message, String detailedMessage = null) {
        createResult(HealthCheckResultType.SUCCESS, name, category, message, detailedMessage)
    }

    HealthCheckRunResult createResult(HealthCheckResultType type, String name, String category, String message, String detailedMessage) {
        def result = metadata.create(HealthCheckRunResult)
        result.result = type
        result.message = message
        result.detailedMessage = detailedMessage
        result.category = category
        result.name = name
        result
    }

    HealthCheckRunResult error(String name, String category, String message, String detailedMessage = null) {
        createResult(HealthCheckResultType.ERROR, name, category, message, detailedMessage)
    }

    HealthCheckRunResult warning(String name, String category, String message, String detailedMessage = null) {
        createResult(HealthCheckResultType.WARNING, name, category, message, detailedMessage)
    }
}
