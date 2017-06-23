package de.diedavids.cuba.healthcheck.web.healthcheckconfiguration

import com.haulmont.cuba.gui.components.AbstractEditor
import de.diedavids.cuba.healthcheck.entity.CustomHealthCheckConfiguration
import de.diedavids.cuba.healthcheck.entity.HealthCheckType

class CustomHealthCheckConfigurationEdit extends AbstractEditor<CustomHealthCheckConfiguration> {


    @Override
    protected void initNewItem(CustomHealthCheckConfiguration item) {
        item.type = HealthCheckType.CUSTOM
        item.active = true
    }

}