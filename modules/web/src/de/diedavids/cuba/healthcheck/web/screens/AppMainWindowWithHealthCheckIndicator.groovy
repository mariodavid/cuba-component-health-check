package de.diedavids.cuba.healthcheck.web.screens

import com.haulmont.cuba.core.global.Security
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.Button
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.actions.BaseAction
import com.haulmont.cuba.security.entity.EntityOp
import com.haulmont.cuba.web.app.mainwindow.AppMainWindow
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import de.diedavids.cuba.healthcheck.entity.HealthCheckReport
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

        initLatestHealthCheckBtn()

        initInitialCheckWindow()
    }

    protected void initInitialCheckWindow() {
        if (healthCheckService.initialSetupScreenNecessary) {
            openWindow('ddchc$InitialCheck', WindowManager.OpenType.DIALOG)
        }
    }
    protected void initLatestHealthCheckBtn() {
        if (security.isEntityOpPermitted(HealthCheckReport, EntityOp.READ)) {

            HealthCheckReport healthCheckReport = healthCheckService.latestHealthCheckReport

            if (healthCheckReport) {
                healthCheckStatusBtn.visible = security.isEntityOpPermitted(HealthCheckReport, EntityOp.READ)
                healthCheckStatusBtn.icon = healthCheckReport.result.icon
                if (healthCheckReport.result == HealthCheckResultType.ERROR) {
                    healthCheckStatusBtn.styleName = 'danger'
                } else {
                    healthCheckStatusBtn.styleName = 'friendly'
                }
                healthCheckStatusBtn.action = new BaseAction('showHealthCheck') {
                    @Override
                    void actionPerform(Component component) {
                        openEditor(healthCheckReport, WindowManager.OpenType.NEW_TAB, [showHistory: true])
                    }
                }
            }
        }
    }
}