package de.diedavids.cuba.healthcheck.core.healthchecks;

import de.diedavids.cuba.healthcheck.data.SimpleDataLoader;
import de.diedavids.cuba.healthcheck.entity.HealthCheckConfiguration;

import javax.inject.Inject;

public abstract class DefaultHealthCheck extends AbstractHealthCheck {

    @Inject
    SimpleDataLoader simpleDataLoader;

    HealthCheckConfiguration configuration;


    @Override
    public HealthCheckConfiguration getConfiguration() {
        if (configuration == null) {
            configuration = simpleDataLoader.loadByProperty(HealthCheckConfiguration.class, "code", getConfigurationCode());
        }
        return configuration;
    }


    protected abstract String getConfigurationCode();
}
