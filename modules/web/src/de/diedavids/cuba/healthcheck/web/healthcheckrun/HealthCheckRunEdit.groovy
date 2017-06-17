package de.diedavids.cuba.healthcheck.web.healthcheckrun

import com.haulmont.cuba.core.global.Messages
import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.Formatter
import com.haulmont.cuba.gui.components.ListComponent
import com.haulmont.cuba.gui.components.Table
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import de.diedavids.cuba.healthcheck.entity.HealthCheckRun
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult

import javax.annotation.Nullable
import javax.inject.Inject

class HealthCheckRunEdit extends AbstractEditor<HealthCheckRun> {

    @Inject
    Table<HealthCheckRunResult> resultsTable

    @Inject
    Messages messages

    @Override
    void init(Map<String, Object> params) {
        super.init(params)

        resultsTable.iconProvider = new ListComponent.IconProvider<HealthCheckRunResult>() {
            @Override
            String getItemIcon(HealthCheckRunResult entity) {
                entity.result.icon
            }
        }



        resultsTable.getColumn('category').setFormatter(new Formatter() {
            @Override
            String format(Object value) {
                messages.getMainMessage("healthCheck.categories.${value}.label")
            }
        })

        resultsTable.setStyleProvider(new Table.StyleProvider<HealthCheckRunResult>() {
            @Override
            String getStyleName(HealthCheckRunResult entity, @Nullable String property) {
                if (entity.result == HealthCheckResultType.ERROR) {
                    'health-check-table-row-failure'
                } else {
                    'health-check-table-row-success'
                }
            }
        })
    }

    public void close() {
        close(CLOSE_ACTION_ID)
    }
}