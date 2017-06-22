package de.diedavids.cuba.healthcheck.core

import com.google.common.base.Strings
import com.haulmont.bali.util.Dom4j
import com.haulmont.cuba.core.global.Resources
import com.haulmont.cuba.core.sys.AppContext
import org.apache.commons.io.IOUtils
import org.apache.commons.lang.text.StrTokenizer
import org.dom4j.Element
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

import javax.inject.Inject
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * Class is used for loading and storing of predefined Health checks that are used by the Health Check Runner. Health Checks are loaded
 * from configuration files defined by the {@code ddchc.healthCheckConfig} application property.
 * <p>
 * Health Checks with the name defined by the {@link #ALL_ENTITIES_QUERY_NAME} field should not be present in the checks
 * config. If the query with this name is requested, the {@link HealthCheckInfo} for the query that returns all entities will
 * be returned.
 */

@Component("ddchc_HealthCheckConfigurationLoader")
class HealthCheckConfigurationLoader {

    protected final String CUBA_HEALTH_CHECK_CONFIG_PROP_NAME = "ddchc.healthCheckConfig"

    private final Logger log = LoggerFactory.getLogger(HealthCheckConfigurationLoader.class)

    protected volatile boolean initialized

    protected ReadWriteLock lock = new ReentrantReadWriteLock()

    @Inject
    protected Resources resources

    protected List<HealthCheckInfo> checks = new ArrayList<>()

    static final String ALL_ENTITIES_QUERY_NAME = "all"


    List<HealthCheckInfo> getHealthChecks() {
        lock.readLock().lock()
        try {
            checkInitialized()
            checks
        } finally {
            lock.readLock().unlock()
        }
    }

    protected void checkInitialized() {
        if (!initialized) {
            lock.readLock().unlock()
            lock.writeLock().lock()
            try {
                if (!initialized) {
                    init()
                    initialized = true
                }
            } finally {
                lock.readLock().lock()
                lock.writeLock().unlock()
            }
        }
    }

    protected void init() {
        String configName = getAppContextProperty(CUBA_HEALTH_CHECK_CONFIG_PROP_NAME)
        StrTokenizer tokenizer = new StrTokenizer(configName)
        for (String location : tokenizer.getTokenArray()) {
            Resource resource = resources.getResource(location)
            if (resource.exists()) {
                InputStream stream = null
                try {
                    stream = resource.getInputStream()
                    loadConfig(Dom4j.readDocument(stream).getRootElement())
                } catch (IOException e) {
                    throw new RuntimeException(e)
                } finally {
                    IOUtils.closeQuietly(stream)
                }
            } else {
                log.warn("Resource " + location + " not found, ignore it")
            }
        }
    }

    protected String getAppContextProperty(String propertyName) {
        AppContext.getProperty(propertyName)
    }

    protected void loadConfig(Element rootElem) {
        for (Element queryElem : Dom4j.elements(rootElem, "databaseEntityHealthCheck")) {
            createDatabaseEntityHealthCheck(queryElem)
        }
        for (Element queryElem : Dom4j.elements(rootElem, "httpConnectionHealthCheck")) {
            createHttpConnectionHealthCheck(queryElem)
        }
    }

    protected void createDatabaseEntityHealthCheck(Element queryElem) {
        String queryName = queryElem.attributeValue("name")
        if (ALL_ENTITIES_QUERY_NAME.equalsIgnoreCase(queryName)) {
            log.error("{} is a predefined query name. It can not be used.", queryName)
        }
        String entityName = queryElem.attributeValue("entity")
        String jpql = queryElem.elementText("jpql")

        if (Strings.isNullOrEmpty(queryName)) {
            log.error("queryName attribute is not defined")
        }
        if (Strings.isNullOrEmpty(entityName)) {
            log.error("entityName attribute is not defined")
        }
        if (Strings.isNullOrEmpty(jpql)) {
            log.error("Query jpql is not defined")
        }

        HealthCheckInfo healthCheckInfo = new DatabaseEntityHealthCheckInfo(
                name: queryName,
                entityName: entityName,
                jpql: jpql
        )

        Element paramsEl = queryElem.element("params")
        if (paramsEl != null) {
            for (Element paramElem : Dom4j.elements(paramsEl, "param")) {
                String paramName = paramElem.attributeValue("name")
                String paramType = paramElem.attributeValue("type")
                HealthCheckParamInfo param = new HealthCheckParamInfo(name: paramName, type: paramType)
                healthCheckInfo.getParams().add(param)
            }
        }

        checks.add(healthCheckInfo)
    }

    protected void createHttpConnectionHealthCheck(Element queryElem) {
        String queryName = queryElem.attributeValue("name")

        if (Strings.isNullOrEmpty(queryName)) {
            log.error("queryName attribute is not defined")
        }

        HealthCheckInfo healthCheckInfo = new HttpConnectionHealthCheckInfo(
                name: queryName,
        )

        checks.add(healthCheckInfo)
    }

    /**
     * Class stores an information about the predefined JPQL query
     */
    static class HealthCheckInfo {
        String name
    }

    static class DatabaseEntityHealthCheckInfo extends HealthCheckInfo {
        String jpql
        String entityName
        List<HealthCheckParamInfo> params = []
    }
    static class HttpConnectionHealthCheckInfo extends HealthCheckInfo {
    }

    /**
     * Class stores an information about the predefined JPQL query parameter
     */
    static class HealthCheckParamInfo extends HealthCheckInfo {
        String type
    }
}
