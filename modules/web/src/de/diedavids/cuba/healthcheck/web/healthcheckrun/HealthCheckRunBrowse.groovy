package de.diedavids.cuba.healthcheck.web.healthcheckrun

import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.data.GroupDatasource
import de.diedavids.cuba.healthcheck.entity.HealthCheckRun
import de.diedavids.cuba.healthcheck.service.HealthCheckService

import javax.inject.Inject

class HealthCheckRunBrowse extends AbstractLookup {

    @Inject
    HealthCheckService healthCheckService

    @Inject
    GroupDatasource<HealthCheckRun, UUID> healthCheckRunsDs


    void runHealthChecks() {
        healthCheckService.runHealthChecks()

        healthCheckRunsDs.refresh()


    }

}