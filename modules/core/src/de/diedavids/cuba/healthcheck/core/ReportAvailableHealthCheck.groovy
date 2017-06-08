package de.diedavids.cuba.healthcheck.core

import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.cuba.security.entity.Role
import org.springframework.stereotype.Component

import javax.inject.Inject

@Component
class ReportAvailableHealthCheck extends DatabaseEntityInstanceAvailableHealthCheck<Role> {

    public static final String MY_ROLE_NAME = "my-role"

    @Inject
    DataManager dataManager

    @Override
    protected LoadContext<Role> createSingleEntityLoadContext() {
        LoadContext.create(Role)
                .setQuery(LoadContext.createQuery('select e from sec$Role e where e.name = :roleName')
                .setParameter("roleName", MY_ROLE_NAME)
        )
    }
}
