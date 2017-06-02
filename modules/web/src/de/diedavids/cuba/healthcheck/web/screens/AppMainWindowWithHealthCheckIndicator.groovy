package de.diedavids.cuba.healthcheck.web.screens

import com.haulmont.cuba.core.global.Security
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.Button
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.actions.BaseAction
import com.haulmont.cuba.security.entity.EntityOp
import com.haulmont.cuba.web.app.mainwindow.AppMainWindow
import de.diedavids.cuba.healthcheck.entity.HealthCheckRun
import de.diedavids.cuba.healthcheck.service.HealthCheckService

import javax.inject.Inject

public class AppMainWindowWithHealthCheckIndicator extends AppMainWindow {

    @Inject
    Button healthCheckStatusBtn

    @Inject
    HealthCheckService healthCheckService

    @Inject
    Security security

    @Override
    void ready() {
        super.ready()

        if (security.isEntityOpPermitted(HealthCheckRun, EntityOp.READ)) {

            HealthCheckRun healthCheckRun = healthCheckService.getLatestHealthCheck()

            if (healthCheckRun) {
                healthCheckStatusBtn.visible = security.isEntityOpPermitted(HealthCheckRun, EntityOp.READ)
                healthCheckStatusBtn.icon = healthCheckRun.result.icon
                healthCheckStatusBtn.action = new BaseAction('showHealthCheck') {
                    @Override
                    void actionPerform(Component component) {
                        openEditor(healthCheckRun, WindowManager.OpenType.NEW_TAB)
                    }
                }
            }
        }
    }
}