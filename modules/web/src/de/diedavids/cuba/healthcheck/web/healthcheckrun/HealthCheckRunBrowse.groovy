package de.diedavids.cuba.healthcheck.web.healthcheckrun

import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Button
import com.haulmont.cuba.gui.components.ListComponent
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.data.GroupDatasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import de.diedavids.cuba.healthcheck.entity.HealthCheckRun
import de.diedavids.cuba.healthcheck.service.HealthCheckService

import javax.annotation.Nullable
import javax.inject.Inject

class HealthCheckRunBrowse extends AbstractLookup {

    @Inject
    Table<HealthCheckRun> healthCheckRunsTable

    @Inject
    HealthCheckService healthCheckService

    @Inject
    GroupDatasource<HealthCheckRun, UUID> healthCheckRunsDs

    @Inject
    ComponentsFactory componentsFactory

    @Inject
    Button runHealthChecksBtn

    @Override
    void init(Map<String, Object> params) {
        super.init(params)

        healthCheckRunsTable.iconProvider = new ListComponent.IconProvider<HealthCheckRun>() {
            @Override
            String getItemIcon(HealthCheckRun entity) {
                entity.result.icon
            }
        }
        healthCheckRunsTable.setStyleProvider(new Table.StyleProvider<HealthCheckRun>() {
            @Override
            String getStyleName(HealthCheckRun entity, @Nullable String property) {
                if (entity.result == HealthCheckResultType.ERROR) {
                    'health-check-table-row-failure'
                }
                else {
                    'health-check-table-row-success'
                }
            }
        })
    }

    void runHealthChecks() {

        healthCheckService.runHealthChecks()
        healthCheckRunsDs.refresh()
        runHealthChecksBtn.enabled = true
    }

}