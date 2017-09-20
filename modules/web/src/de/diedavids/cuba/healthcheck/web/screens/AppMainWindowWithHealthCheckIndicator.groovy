package de.diedavids.cuba.healthcheck.web.screens

import com.haulmont.cuba.core.global.Messages
import com.haulmont.cuba.core.global.Security
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.Button
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.Frame
import com.haulmont.cuba.gui.components.actions.BaseAction
import com.haulmont.cuba.security.entity.EntityOp
import com.haulmont.cuba.web.app.mainwindow.AppMainWindow
import de.diedavids.cuba.healthcheck.entity.HealthCheckReport
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import de.diedavids.cuba.healthcheck.service.HealthCheckService

import javax.inject.Inject

public class AppMainWindowWithHealthCheckIndicator extends AppMainWindow {

    @Inject
    Button healthCheckStatusBtn

    @Inject
    HealthCheckService healthCheckService

    @Inject
    Security security

    @Inject
    Messages messages


    private String INITIAL_CHECK_SCREEN = 'ddchc$InitialCheck'

    @Override
    void ready() {
        super.ready()

        initLatestHealthCheckBtn()

        initInitialCheckWindow()
    }

    protected void initInitialCheckWindow() {

        if (healthCheckService.initialSetupScreenNecessary) {

            if (healthCheckReportReadable && initialCheckScreenAllowed) {
                openWindow(INITIAL_CHECK_SCREEN, WindowManager.OpenType.DIALOG)
            }
            else {
                def msg = messages.getMessage('de.diedavids.cuba.healthcheck.web.screens', 'initialCheckAdministratorRequired')
                showNotification(msg, Frame.NotificationType.ERROR)
            }
        }
    }

    protected void initLatestHealthCheckBtn() {
        if (healthCheckReportReadable) {

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

    protected boolean isHealthCheckReportReadable() {
        security.isEntityOpPermitted(HealthCheckReport, EntityOp.READ)
    }

    protected boolean isInitialCheckScreenAllowed() {
        security.isScreenPermitted(INITIAL_CHECK_SCREEN)
    }
}