package de.diedavids.cuba.healthcheck.web.healthcheckreport

import com.haulmont.cuba.core.global.Messages
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.WindowParam
import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.Button
import com.haulmont.cuba.gui.components.Formatter
import com.haulmont.cuba.gui.components.ListComponent
import com.haulmont.cuba.gui.components.Table
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import de.diedavids.cuba.healthcheck.entity.HealthCheckReport
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail
import de.diedavids.cuba.healthcheck.service.HealthCheckService
import de.diedavids.cuba.healthcheck.web.screens.LatestHealthCheckOpener

import javax.annotation.Nullable
import javax.inject.Inject

class HealthCheckReportEdit extends AbstractEditor<HealthCheckReport> {

    @Inject
    Table<HealthCheckReportDetail> checksTable

    @Inject
    Messages messages

    @Inject
    Button historyBtn


    @Inject
    HealthCheckService healthCheckService

    @WindowParam
    Boolean showHistory

    @Override
    void init(Map<String, Object> params) {

        super.init(params)

        checksTable.iconProvider = new ListComponent.IconProvider<HealthCheckReportDetail>() {
            @Override
            String getItemIcon(HealthCheckReportDetail entity) {
                entity.result.icon
            }
        }



        checksTable.getColumn('category').setFormatter(new Formatter() {
            @Override
            String format(Object value) {
                messages.getMainMessage("healthCheck.categories.${value}.label")
            }
        })

        checksTable.setStyleProvider(new Table.StyleProvider<HealthCheckReportDetail>() {
            @Override
            String getStyleName(HealthCheckReportDetail entity, @Nullable String property) {
                if (entity.result == HealthCheckResultType.ERROR) {
                    'health-check-table-row-failure'
                } else {
                    'health-check-table-row-success'
                }
            }
        })

    }

    void close() {
        close(CLOSE_ACTION_ID)
    }

    void history() {
        openWindow('ddchc$HealthCheckReport.browse', WindowManager.OpenType.NEW_TAB)
    }


    void runHealthChecks() {

        healthCheckService.runHealthChecks()
        closeAndRun(CLOSE_ACTION_ID, new LatestHealthCheckOpener())
    }
}