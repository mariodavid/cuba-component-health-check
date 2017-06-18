package de.diedavids.cuba.healthcheck.service

import com.haulmont.cuba.core.global.*
import de.diedavids.cuba.healthcheck.HealthCheck
import de.diedavids.cuba.healthcheck.core.HealthCheckConfiguration
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import de.diedavids.cuba.healthcheck.entity.HealthCheckRun

import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult
import org.apache.commons.lang.exception.ExceptionUtils
import org.springframework.stereotype.Service

import javax.inject.Inject

@Service(HealthCheckService.NAME)
class HealthCheckServiceBean implements HealthCheckService {


    @Inject
    HealthCheckConfiguration healthCheckConfiguration

    @Inject
    DataManager dataManager

    @Inject
    Metadata metadata

    @Inject
    TimeSource timeSource

    @Override
    HealthCheckRun runHealthChecks() {

        List<HealthCheckConfiguration.HealthCheckInfo> healthCheckInfos = healthCheckConfiguration.healthChecks


        def run = createHealthCheckRun()
        runChecks(run,getProgrammaticallyDefinedChecks())
        calculateHealthCheckResult(run)
        saveHealthCheckRun(run)

        run
    }


    @Override
    HealthCheckRun getLatestHealthCheck() {

        LoadContext loadContext = LoadContext.create(HealthCheckRun)
                .setQuery(
                LoadContext.createQuery('select e from ddchc$HealthCheckRun e order by e.executedAt desc').setMaxResults(1)

        )

        dataManager.load(loadContext)
    }

    HealthCheckRun runChecks(HealthCheckRun run, Map<String, HealthCheck> healthChecks) {

        run.executedAt = timeSource.currentTimestamp()
        List<HealthCheckRunResult> results = []

        healthChecks.each { String name, HealthCheck healthCheck ->
            HealthCheckRunResult result
            try {
                result = healthCheck.check()
            }
            catch (Exception e) {
                result = createExceptionalRunResult(e, healthCheck)
            }
            result.healthCheckRun = run
            results << result
        }


        if (!run.results) {
         run.results = []
        }
        run.results.addAll(results)
        run
    }

    private void calculateHealthCheckResult(HealthCheckRun run) {
        run.result = getRunResultTypeFromResults(run.results)
    }

    private Map<String, HealthCheck> getProgrammaticallyDefinedChecks() {
        AppBeans.getAll(HealthCheck)
    }

    private HealthCheckRun createHealthCheckRun() {
        metadata.create(HealthCheckRun)
    }

    private HealthCheckRunResult createExceptionalRunResult(Exception e, HealthCheck healthCheck) {
        HealthCheckRunResult result = metadata.create(HealthCheckRunResult)
        result.result = HealthCheckResultType.ERROR
        result.message = e.message
        result.category = healthCheck.category
        result.name = healthCheck.name
        result.detailedMessage = createDetailedMessageFromException(e)
        result
    }

    private String createDetailedMessageFromException(Exception e) {
        def result = null
        def stacktraceMessage = ExceptionUtils.getStackTrace(e)
        if (stacktraceMessage.size() > 4000) {
            result = stacktraceMessage.substring(0, 3999)
        } else {
            result = stacktraceMessage
        }

        result
    }
    private HealthCheckResultType getRunResultTypeFromResults(Collection<HealthCheckRunResult> results) {
        def everyResultSuccess = results.every { it.result == HealthCheckResultType.SUCCESS }

        everyResultSuccess ? HealthCheckResultType.SUCCESS : HealthCheckResultType.ERROR
    }

    void saveHealthCheckRun(HealthCheckRun run) {

        CommitContext commitContext = new CommitContext()
        commitContext.addInstanceToCommit(run)

        run.results.each {
            commitContext.addInstanceToCommit(it)
        }

        dataManager.commit(commitContext)


    }
}