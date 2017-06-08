package de.diedavids.cuba.healthcheck.service

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheckRegistry
import com.haulmont.cuba.core.global.*
import de.diedavids.cuba.healthcheck.core.HealthCheckConfiguration
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import de.diedavids.cuba.healthcheck.entity.HealthCheckRun
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult
import org.springframework.stereotype.Service

import javax.inject.Inject

@Service(HealthCheckService.NAME)
class HealthCheckServiceBean implements HealthCheckService {


    @Inject
    HealthCheckConfiguration healthCheckConfiguration
    @Inject
    HealthCheckRegistry healthCheckRegistry

    @Inject
    DataManager dataManager

    @Inject
    Metadata metadata

    @Inject
    TimeSource timeSource
    @Override
    HealthCheckRun runHealthChecks() {

        List<HealthCheckConfiguration.HealthCheckInfo> healthCheckInfos = healthCheckConfiguration.healthChecks


        SortedMap<String, HealthCheck.Result> healthCheckResults = healthCheckRegistry.runHealthChecks()

        saveResults(healthCheckResults)
    }

    @Override
    HealthCheckRun getLatestHealthCheck() {

        LoadContext loadContext = LoadContext.create(HealthCheckRun)
                .setQuery(
                LoadContext.createQuery('select e from ddchc$HealthCheckRun e order by e.executedAt desc').setMaxResults(1)

        )

        dataManager.load(loadContext)
    }

    HealthCheckRun saveResults(Map<String, HealthCheck.Result> healthCheckResults) {

        def run = metadata.create(HealthCheckRun)

        CommitContext commitContext = new CommitContext()
        run.executedAt = timeSource.currentTimestamp()
        List<HealthCheckRunResult> results = []


        commitContext.addInstanceToCommit(run)

        healthCheckResults.each {
            def runResult = metadata.create(HealthCheckRunResult)

            runResult.name = it.key
            def resultType
            if (it.value.healthy) {
                resultType = HealthCheckResultType.SUCCESS
            }
            else {
                resultType = HealthCheckResultType.ERROR
            }

            runResult.result = resultType
            runResult.healthCheckRun = run
            results << runResult

            commitContext.addInstanceToCommit(runResult)
        }

        run.result = results.every {it.result == HealthCheckResultType.SUCCESS} ? HealthCheckResultType.SUCCESS : HealthCheckResultType.ERROR

        run.results = results
        dataManager.commit(commitContext)

        run
    }
}