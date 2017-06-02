package de.diedavids.cuba.healthcheck.core

import com.codahale.metrics.health.HealthCheck
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.cuba.security.entity.Role
import org.springframework.stereotype.Component

import javax.inject.Inject

@Component
class ReportAvailableHealthCheck extends HealthCheck {

    public static final String MY_ROLE_NAME = "my-role"
    @Inject
    DataManager dataManager

    @Override
    protected HealthCheck.Result check() throws Exception {

        LoadContext loadContext = LoadContext.create(Role)
                .setQuery(LoadContext.createQuery('select e from sec$Role e where e.name = :roleName')
                .setParameter("roleName", MY_ROLE_NAME)
        )

        Role item = dataManager.load(loadContext)

        if (item) {
            return HealthCheck.Result.healthy("Role found")
        }
        else {
            return HealthCheck.Result.unhealthy("Role ($MY_ROLE_NAME) was not found in the database")
        }
    }
}
