package de.diedavids.cuba.healthcheck.web.healthcheckconfiguration

import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.components.Window
import com.haulmont.cuba.gui.data.GroupDatasource
import de.diedavids.cuba.healthcheck.entity.CustomHealthCheckConfiguration
import de.diedavids.cuba.healthcheck.entity.HealthCheckConfiguration

import javax.inject.Inject

class HealthCheckConfigurationBrowse extends AbstractLookup {

    @Inject
    GroupDatasource<HealthCheckConfiguration, UUID> healthCheckConfigurationsDs
    @Inject
    Table<HealthCheckConfiguration> healthCheckConfigurationsTable

    @Inject
    Metadata metadata

    void create() {
        def configuration = metadata.create(CustomHealthCheckConfiguration)
        def editor = openEditor(configuration, WindowManager.OpenType.THIS_TAB)
        editor.addCloseWithCommitListener(new DatasourceRefresher())
    }

    void edit() {
        def editor = openEditor(healthCheckConfigurationsTable.singleSelected, WindowManager.OpenType.THIS_TAB)
        editor.addCloseWithCommitListener(new DatasourceRefresher())
    }

    class DatasourceRefresher implements Window.CloseWithCommitListener {
        @Override
        void windowClosedWithCommitAction() {
            healthCheckConfigurationsDs.refresh()
        }
    }
}
