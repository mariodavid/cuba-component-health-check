package de.diedavids.cuba.healthcheck.core

import com.haulmont.cuba.core.global.Resources
import de.diedavids.cuba.healthcheck.TestHealthCheckConfiguration
import org.springframework.core.io.ByteArrayResource
import spock.lang.Specification

class HealthCheckConfigurationSpec extends Specification {
    Resources resources
    TestHealthCheckConfiguration sut

    def setup() {
        resources = Mock(Resources)
        sut = new TestHealthCheckConfiguration(
                resources: resources,
                mockedAppContextProperty: '/de/diedavids/cuba/healtchecks.xml'
        )
    }

    def "healthChecks creates a DatabaseEntityHealthCheckInfo from the corresponding tag"() {

        given:
        mockXML('''<checks>
    <databaseEntityHealthCheck name="roleExistsDeclarativly" entity="sec$Role">
        <jpql><![CDATA[select e from sec$Role e where e.name = 'my-declarative-role']]></jpql>
    </databaseEntityHealthCheck>
</checks>''')

        when:
        def allHealthChecks = sut.healthChecks
        HealthCheckConfiguration.DatabaseEntityHealthCheckInfo declarativeCheck = allHealthChecks[0]

        then:
        declarativeCheck instanceof HealthCheckConfiguration.DatabaseEntityHealthCheckInfo
        declarativeCheck.name == 'roleExistsDeclarativly'
        declarativeCheck.jpql == "select e from sec\$Role e where e.name = 'my-declarative-role'"
    }
    def "healthChecks creates a HttpConnectionHealthCheckInfo from the corresponding tag"() {

        given:
        mockXML('''<checks>
    <httpConnectionHealthCheckInfo name="httpConnectionCheck" />
</checks>''')

        when:
        def allHealthChecks = sut.healthChecks
        HealthCheckConfiguration.HttpConnectionHealthCheckInfo declarativeCheck = allHealthChecks[0]

        then:
        declarativeCheck instanceof HealthCheckConfiguration.HttpConnectionHealthCheckInfo
        declarativeCheck.name == 'httpConnectionCheck'
    }


    private void mockXML(String xmlString) {
        resources.getResource(_) >> new ByteArrayResource(xmlString.bytes)
    }
}
