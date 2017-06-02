package de.diedavids.cuba.healthcheck.web.healthcheckrun

import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.ListComponent
import com.haulmont.cuba.gui.components.Table
import de.diedavids.cuba.healthcheck.entity.HealthCheckRun
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult

import javax.inject.Inject

class HealthCheckRunEdit extends AbstractEditor<HealthCheckRun> {

    @Inject
    Table<HealthCheckRunResult> resultsTable

    @Override
    void init(Map<String, Object> params) {
        super.init(params)

        resultsTable.iconProvider = new ListComponent.IconProvider<HealthCheckRunResult>() {
            @Override
            String getItemIcon(HealthCheckRunResult entity) {
                entity.result.icon
            }
        }
    }
}