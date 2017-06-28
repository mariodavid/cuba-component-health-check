package de.diedavids.cuba.healthcheck.core.healthchecks

import com.haulmont.cuba.core.global.Scripting
import de.diedavids.cuba.healthcheck.entity.CustomHealthCheckConfiguration
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import org.apache.commons.lang.exception.ExceptionUtils
import org.codehaus.groovy.runtime.MethodClosure

class CustomScriptHealthCheck extends AbstractHealthCheck {

    public static final String ERROR_METHOD_NAME = 'error'
    public static final String WARNING_METHOD_NAME = 'warning'
    public static final String SUCCESS_METHOD_NAME = 'success'
    CustomHealthCheckConfiguration configuration

    Scripting scripting

    @Override
    HealthCheckReportDetail check() {
        def result = healtCheckReportDetailFactory.error(configuration, '', '')

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

        result
    }

    protected Binding initBinding() {
        def binding = new Binding()
        binding.setProperty(ERROR_METHOD_NAME, new MethodClosure(this, ERROR_METHOD_NAME))
        binding.setProperty(WARNING_METHOD_NAME, new MethodClosure(this, WARNING_METHOD_NAME))
        binding.setProperty(SUCCESS_METHOD_NAME, new MethodClosure(this, SUCCESS_METHOD_NAME))
        binding
    }

    private String createDetailedMessageFromException(Exception e) {
        ExceptionUtils.getStackTrace(e)
    }

}
