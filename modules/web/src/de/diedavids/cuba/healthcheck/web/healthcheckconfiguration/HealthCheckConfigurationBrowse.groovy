package de.diedavids.cuba.healthcheck.web.healthcheckconfiguration

import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.components.actions.CreateAction
import de.diedavids.cuba.healthcheck.entity.HealthCheckConfiguration
import de.diedavids.cuba.healthcheck.entity.HealthCheckType

import javax.inject.Inject

class HealthCheckConfigurationBrowse extends AbstractLookup {

    @Inject
    Table<HealthCheckConfiguration> healthCheckConfigurationsTable

    @Inject
    Metadata metadata

    @Override
    void init(Map<String, Object> params) {
        super.init(params)

        CreateAction createAction = healthCheckConfigurationsTable.getAction('create')
        createAction.windowId = 'ddchc$CustomHealthCheckConfiguration.edit'
    }

}