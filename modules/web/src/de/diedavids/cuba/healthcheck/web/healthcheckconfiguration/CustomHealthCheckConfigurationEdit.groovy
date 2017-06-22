package de.diedavids.cuba.healthcheck.web.healthcheckconfiguration

import com.haulmont.cuba.gui.components.AbstractEditor
import de.diedavids.cuba.healthcheck.entity.HealthCheckConfiguration
import de.diedavids.cuba.healthcheck.entity.HealthCheckType

class CustomHealthCheckConfigurationEdit extends AbstractEditor<HealthCheckConfiguration> {


    @Override
    protected void initNewItem(HealthCheckConfiguration item) {
        item.type = HealthCheckType.CUSTOM
        item.active = true
    }

}