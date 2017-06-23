package de.diedavids.cuba.healthcheck.core.healthchecks

import com.haulmont.cuba.core.global.Scripting
import de.diedavids.cuba.healthcheck.entity.CustomHealthCheckConfiguration
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import org.apache.commons.lang.exception.ExceptionUtils
import org.codehaus.groovy.runtime.MethodClosure

class CustomScriptHealthCheck extends AbstractHealthCheck {

    CustomHealthCheckConfiguration configuration
    Scripting scripting

    @Override
    HealthCheckReportDetail check() {
        def result = healtCheckReportDetailFactory.error(configuration, "", "")

        Binding binding = initBinding()
        try {
            def resultFromScript = scripting.evaluateGroovy(configuration.script, binding)

            if (resultFromScript instanceof HealthCheckReportDetail) {
                result = resultFromScript
            }

        }
        catch (Exception e) {
            result.result = HealthCheckResultType.ERROR
            result.detailedMessage = createDetailedMessageFromException(e)
        }

        result.configuration = configuration

        return result
    }

    protected Binding initBinding() {
        def binding = new Binding()
        binding.setProperty("error", new MethodClosure(this, "error"))
        binding.setProperty("warning", new MethodClosure(this, "warning"))
        binding.setProperty("success", new MethodClosure(this, "success"))
        binding
    }

    private String createDetailedMessageFromException(Exception e) {
        ExceptionUtils.getStackTrace(e)
    }

}
