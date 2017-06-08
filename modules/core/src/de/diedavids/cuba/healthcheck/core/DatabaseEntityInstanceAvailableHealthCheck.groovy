package de.diedavids.cuba.healthcheck.core

import com.codahale.metrics.health.HealthCheck
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext

import javax.inject.Inject

/**
 * Abstract superclass for defining a check against the database for a particular entity instance in-place
 *
 * This is normally useful if you want to entfoce some database entries to exists, like
 * - reports with certain codes
 * - security groups or constraints
 * - bpm definitions
 */
abstract class DatabaseEntityInstanceAvailableHealthCheck<T> extends HealthCheck {

    @Inject
    DataManager dataManager

    @Override
    protected HealthCheck.Result check() throws Exception {

        LoadContext loadContext = createSingleEntityLoadContext()

        T item = dataManager.load(loadContext)

        if (item) {
            return HealthCheck.Result.healthy("Entity instance found")
        }
        else {
            return HealthCheck.Result.unhealthy("Entity instance was not found in the database")
        }
    }

    /**
     * defines the LoadContext for the query for the single entity instance
     * that should get loaded from the database
     * @return the loadContext describing the load of the single entity instance
     */
    abstract protected LoadContext<T> createSingleEntityLoadContext()
}
