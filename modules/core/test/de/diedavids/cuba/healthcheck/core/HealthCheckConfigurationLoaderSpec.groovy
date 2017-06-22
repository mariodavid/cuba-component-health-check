package de.diedavids.cuba.healthcheck.core

import com.haulmont.cuba.core.global.Resources
import de.diedavids.cuba.healthcheck.TestHealthCheckConfigurationLoader
import org.springframework.core.io.ByteArrayResource
import spock.lang.Specification

class HealthCheckConfigurationLoaderSpec extends Specification {
    Resources resources
    TestHealthCheckConfigurationLoader sut

    def setup() {
        resources = Mock(Resources)
        sut = new TestHealthCheckConfigurationLoader(
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
        HealthCheckConfigurationLoader.DatabaseEntityHealthCheckInfo declarativeCheck = allHealthChecks[0]

        then:
        declarativeCheck instanceof HealthCheckConfigurationLoader.DatabaseEntityHealthCheckInfo
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
        HealthCheckConfigurationLoader.HttpConnectionHealthCheckInfo declarativeCheck = allHealthChecks[0]

        then:
        declarativeCheck instanceof HealthCheckConfigurationLoader.HttpConnectionHealthCheckInfo
        declarativeCheck.name == 'httpConnectionCheck'
    }


    private void mockXML(String xmlString) {
        resources.getResource(_) >> new ByteArrayResource(xmlString.bytes)
    }
}
