package de.diedavids.cuba.healthcheck

import de.diedavids.cuba.healthcheck.core.HealthCheckConfigurationLoader;

class TestHealthCheckConfigurationLoader extends HealthCheckConfigurationLoader {

    String mockedAppContextProperty

    @Override
    protected String getAppContextProperty(String propertyName) {
        mockedAppContextProperty
    }
}
