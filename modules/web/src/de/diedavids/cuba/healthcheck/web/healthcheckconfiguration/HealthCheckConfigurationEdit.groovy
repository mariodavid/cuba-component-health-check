package de.diedavids.cuba.healthcheck.web.healthcheckconfiguration

import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.FieldGroup
import com.haulmont.cuba.gui.components.LookupField
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
    void init(Map<String, Object> params) {
        super.init(params)

        initHealthCheckClassLookupField()
    }

    @Override
    protected void initNewItem(HealthCheckConfiguration item) {
        item.type = HealthCheckType.CUSTOM
        item.active = true

        fieldGroup.getField('healthCheckClass').visible = false
    }

    protected void initHealthCheckClassLookupField() {
        def property = 'healthCheckClass'

        LookupField lookupField = componentsFactory.createComponent(LookupField.NAME)
        lookupField.setDatasource(healthCheckConfigurationDs, property)
        lookupField.optionsMap = healthCheckService.programmaticallyDefinedChecksMap
        setFieldComponent(property,lookupField)
    }

    protected void setFieldComponent(String property, LookupField lookupField) {
        def fieldConfig = fieldGroup.getField(property)
        fieldConfig.component = lookupField
    }

}