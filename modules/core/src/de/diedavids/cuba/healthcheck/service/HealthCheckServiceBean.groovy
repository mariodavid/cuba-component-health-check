package de.diedavids.cuba.healthcheck.service

import com.haulmont.cuba.core.global.*
import de.diedavids.cuba.healthcheck.HealthCheck
import de.diedavids.cuba.healthcheck.core.HealthCheckConfigurationLoader
import de.diedavids.cuba.healthcheck.core.healthchecks.CustomScriptHealthCheck
import de.diedavids.cuba.healthcheck.data.SimpleDataLoader
import de.diedavids.cuba.healthcheck.entity.HealtCheckReportDetailFactory
import de.diedavids.cuba.healthcheck.entity.HealthCheckConfiguration
import de.diedavids.cuba.healthcheck.entity.HealthCheckReport
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import org.apache.commons.lang.exception.ExceptionUtils
import org.springframework.stereotype.Service

import javax.inject.Inject

@Service(HealthCheckService.NAME)
class HealthCheckServiceBean implements HealthCheckService {


    @Inject
    HealthCheckConfigurationLoader healthCheckConfiguration

    @Inject
    DataManager dataManager

    @Inject
    SimpleDataLoader simpleDataLoader

    @Inject
    Metadata metadata

    @Inject
    TimeSource timeSource

    @Inject
    Scripting scripting

    @Inject
    HealtCheckReportDetailFactory healtCheckReportDetailFactory

    @Override
    HealthCheckReport runHealthChecks() {

//        List<HealthCheckConfigurationLoader.HealthCheckInfo> healthCheckInfos = healthCheckConfiguration.healthChecks

        def run = createHealthCheckRun()
        runChecks(run, programmaticallyDefinedChecks)
        runChecks(run, customDefinedChecks)
        calculateHealthCheckResult(run)
        saveHealthCheckRun(run)

        run
    }


    @Override
    HealthCheckReport getLatestHealthCheckReport() {

        LoadContext loadContext = LoadContext.create(HealthCheckReport)
                .setQuery(
                LoadContext.createQuery('select e from ddchc$HealthCheckReport e order by e.executedAt desc').setMaxResults(1)

        )
        dataManager.load(loadContext)
    }

    HealthCheckReport runChecks(HealthCheckReport run, Map<String, HealthCheck> healthChecks) {

        run.executedAt = timeSource.currentTimestamp()
        List<HealthCheckReportDetail> results = []

        healthChecks.each { String name, HealthCheck healthCheck ->
            if (isCheckActive(healthCheck)) {
                HealthCheckReportDetail result
                try {
                    result = healthCheck.check()
                }
                catch (Exception e) {
                    result = createExceptionalRunResult(e, healthCheck)
                }
                result.healthCheckReport = run
                if (!result.configuration) {
                    result.configuration = healthCheck.configuration
                }
                results << result
            }
        }


        if (!run.checks) {
            run.checks = []
        }
        run.checks.addAll(results)
        run
    }

    private boolean isCheckActive(HealthCheck healthCheck) {
            return healthCheck?.configuration?.active
    }

    private void calculateHealthCheckResult(HealthCheckReport run) {
        run.result = getRunResultTypeFromResults(run.checks)
    }

    Map<String, HealthCheck> getProgrammaticallyDefinedChecks() {
        AppBeans.getAll(HealthCheck)
    }

    Map<String, HealthCheck> getCustomDefinedChecks() {
        LoadContext loadContext = LoadContext.create(HealthCheckConfiguration)
                .setQuery(LoadContext.createQuery('select e from ddchc$HealthCheckConfiguration e where e.type = @enum(de.diedavids.cuba.healthcheck.entity.HealthCheckType.CUSTOM)')
        )

        Collection<HealthCheckConfiguration> customHealthChecks = dataManager.loadList(loadContext)

        customHealthChecks.collectEntries { HealthCheckConfiguration configuration ->
            def check = new CustomScriptHealthCheck(
                    scripting: scripting,
                    healtCheckReportDetailFactory: healtCheckReportDetailFactory,
                    configuration: configuration
            )

            [(configuration.id.toString()): check]
        }

    }

    private HealthCheckReport createHealthCheckRun() {
        metadata.create(HealthCheckReport)
    }

    private HealthCheckReportDetail createExceptionalRunResult(Exception e, HealthCheck healthCheck) {
        HealthCheckReportDetail result = metadata.create(HealthCheckReportDetail)
        result.result = HealthCheckResultType.ERROR
        result.message = e.message
        result.category = healthCheck.category
        result.detailedMessage = createDetailedMessageFromException(e)
        result
    }

    private String createDetailedMessageFromException(Exception e) {
        ExceptionUtils.getStackTrace(e)
    }

    private HealthCheckResultType getRunResultTypeFromResults(Collection<HealthCheckReportDetail> checks) {
        def everyResultSuccess = checks.every { it.result == HealthCheckResultType.SUCCESS }

        everyResultSuccess ? HealthCheckResultType.SUCCESS : HealthCheckResultType.ERROR
    }

    void saveHealthCheckRun(HealthCheckReport run) {

        CommitContext commitContext = new CommitContext()
        commitContext.addInstanceToCommit(run)

        run.checks.each {
            commitContext.addInstanceToCommit(it)
        }

        dataManager.commit(commitContext)


    }
}