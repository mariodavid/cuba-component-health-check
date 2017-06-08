package de.diedavids.cuba.healthcheck

import de.diedavids.cuba.healthcheck.core.HealthCheckConfiguration;

class TestHealthCheckConfiguration extends HealthCheckConfiguration {

    String mockedAppContextProperty

    @Override
    protected String getAppContextProperty(String propertyName) {
        mockedAppContextProperty
    }
}
