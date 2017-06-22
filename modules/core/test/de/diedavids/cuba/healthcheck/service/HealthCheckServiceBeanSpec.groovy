package de.diedavids.cuba.healthcheck.service

import com.haulmont.cuba.core.global.Resources
import de.diedavids.cuba.healthcheck.TestHealthCheckConfigurationLoader
import org.springframework.core.io.ByteArrayResource
import spock.lang.Specification

class HealthCheckServiceBeanSpec extends Specification {

    def "runChecks does something"() {
        def resources = Mock(Resources)
        def xmlString = '''<?xml version="1.0"?>
<checks xmlns="http://schemas.diedavids.de/cuba/health-check/health-check.xsd">
    <check name="roleExistsDeclarativly" entity="sec$Role">
        <jpql><![CDATA[select e from sec$Role e where e.name = 'my-declarative-role']]></jpql>
    </check>
</checks>'''

        resources.getResource(_) >> new ByteArrayResource(xmlString.bytes)

        given:
        def sut = new HealthCheckServiceBean(
                healthCheckConfiguration: new TestHealthCheckConfigurationLoader(
                        resources: resources,
                        mockedAppContextProperty: '/de/diedavids/cuba/healtchecks.xml'
                )
        )
        when:
        sut.runHealthChecks()
        then:
        false
    }
}

