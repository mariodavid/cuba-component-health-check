package de.diedavids.cuba.healthcheck.web.screens

import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.config.WindowConfig
import com.haulmont.cuba.web.App
import de.diedavids.cuba.healthcheck.entity.HealthCheckReport
import de.diedavids.cuba.healthcheck.service.HealthCheckService

class LatestHealthCheckOpener implements Runnable {
    @Override
    void run() {
        HealthCheckService healthCheckService = AppBeans.get(HealthCheckService);
        WindowManager wm = App.getInstance().getWindowManager();

        HealthCheckReport healthCheckReport = healthCheckService.getLatestHealthCheckReport()
        if (healthCheckReport) {
            WindowConfig wc = AppBeans.get(WindowConfig.NAME);
            wm.openEditor(wc.getWindowInfo('ddchc$HealthCheckReport.edit'), healthCheckReport, WindowManager.OpenType.NEW_TAB);
        }
    }
}
