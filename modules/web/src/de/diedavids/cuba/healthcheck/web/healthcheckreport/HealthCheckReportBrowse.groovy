package de.diedavids.cuba.healthcheck.web.healthcheckreport

import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Button
import com.haulmont.cuba.gui.components.ListComponent
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.data.GroupDatasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import de.diedavids.cuba.healthcheck.entity.HealthCheckReport
import de.diedavids.cuba.healthcheck.service.HealthCheckService

import javax.annotation.Nullable
import javax.inject.Inject

class HealthCheckReportBrowse extends AbstractLookup {

    @Inject
    Table<HealthCheckReport> healthCheckRunsTable

    @Inject
    HealthCheckService healthCheckService

    @Inject
    GroupDatasource<HealthCheckReport, UUID> healthCheckRunsDs

    @Inject
    ComponentsFactory componentsFactory

    @Inject
    Button runHealthChecksBtn

    @Override
    void init(Map<String, Object> params) {
        super.init(params)

        healthCheckRunsTable.iconProvider = new ListComponent.IconProvider<HealthCheckReport>() {
            @Override
            String getItemIcon(HealthCheckReport entity) {
                entity.result.icon
            }
        }
        healthCheckRunsTable.setStyleProvider(new Table.StyleProvider<HealthCheckReport>() {
            @Override
            String getStyleName(HealthCheckReport entity, @Nullable String property) {
                if (entity.result == HealthCheckResultType.ERROR) {
                    'health-check-table-row-failure'
                }
                else {
                    'health-check-table-row-success'
                }
            }
        })
    }


}