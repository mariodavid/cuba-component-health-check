package de.diedavids.cuba.healthcheck.core.healthchecks.examples;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.security.entity.Role;
import de.diedavids.cuba.healthcheck.core.healthchecks.DatabaseEntityInstanceAvailableHealthCheck;
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunFrequency;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ReportAvailableHealthCheck extends DatabaseEntityInstanceAvailableHealthCheck<Role> {

    public static final String MY_ROLE_NAME = "my-role";

    @Override
    protected LoadContext<Role> createSingleEntityLoadContext() {
        return LoadContext.create(Role.class).setQuery(LoadContext.createQuery("select e from sec$Role e where e.name = :roleName").setParameter("roleName", MY_ROLE_NAME));
    }

    @Override
    public HealthCheckRunFrequency getFrequency() {
        return HealthCheckRunFrequency.MEDIUM;
    }


}
