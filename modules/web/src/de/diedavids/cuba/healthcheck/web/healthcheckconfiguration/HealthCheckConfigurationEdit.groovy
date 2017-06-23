package de.diedavids.cuba.healthcheck.web.healthcheckconfiguration

import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.FieldGroup
import com.haulmont.cuba.gui.data.Datasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.diedavids.cuba.healthcheck.entity.HealthCheckConfiguration
import de.diedavids.cuba.healthcheck.entity.HealthCheckType
import de.diedavids.cuba.healthcheck.service.HealthCheckService

import javax.inject.Inject

class HealthCheckConfigurationEdit extends AbstractEditor<HealthCheckConfiguration> {

    @Inject
    FieldGroup fieldGroup

    @Inject
    ComponentsFactory componentsFactory

    @Inject
    Datasource<HealthCheckConfiguration> healthCheckConfigurationDs

    @Inject
    HealthCheckService healthCheckService

    @Override
    protected void initNewItem(HealthCheckConfiguration item) {
        item.type = HealthCheckType.CUSTOM
        item.active = true
    }

}