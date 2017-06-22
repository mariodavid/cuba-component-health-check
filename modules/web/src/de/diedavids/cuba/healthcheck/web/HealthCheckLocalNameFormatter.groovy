package de.diedavids.cuba.healthcheck.web

import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.gui.components.Formatter
import de.diedavids.cuba.healthcheck.service.HealthCheckService

class HealthCheckLocalNameFormatter implements Formatter {


    @Override
    String format(Object value) {
        healthCheckServcie.programmaticallyDefinedChecksMap.find {it.value == value}.key
    }

    HealthCheckService getHealthCheckServcie() {
        AppBeans.get(HealthCheckService)
    }

}
