package de.diedavids.cuba.healthcheck.web.healthcheckrun

import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.Label
import com.haulmont.cuba.gui.components.ListComponent
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.components.VBoxLayout
import com.haulmont.cuba.gui.data.GroupDatasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.diedavids.cuba.healthcheck.entity.HealthCheckResultType
import de.diedavids.cuba.healthcheck.entity.HealthCheckRun
import de.diedavids.cuba.healthcheck.entity.HealthCheckRunResult
import de.diedavids.cuba.healthcheck.service.HealthCheckService

import javax.annotation.Nullable
import javax.inject.Inject

class HealthCheckRunBrowse extends AbstractLookup {

    @Inject
    Table<HealthCheckRun> healthCheckRunsTable
    @Inject
    HealthCheckService healthCheckService

    @Inject
    GroupDatasource<HealthCheckRun, UUID> healthCheckRunsDs

    @Inject
    ComponentsFactory componentsFactory

    @Override
    void init(Map<String, Object> params) {
        super.init(params)

//        healthCheckRunsTable.iconProvider = new ListComponent.IconProvider<HealthCheckRun>() {
//            @Override
//            String getItemIcon(HealthCheckRun entity) {
//                entity.result.icon
//            }
//        }
//        healthCheckRunsTable.setStyleProvider(new Table.StyleProvider<HealthCheckRun>() {
//            @Override
//            String getStyleName(HealthCheckRun entity, @Nullable String property) {
//                return "red"
//            }
//        })
    }

    @Override
    void ready() {
        super.ready()

        healthCheckRunsTable.addGeneratedColumn('Result', new Table.ColumnGenerator<HealthCheckRun>() {
            @Override
            Component generateCell(HealthCheckRun entity) {
                def label = componentsFactory.createComponent(Label)
                def layout = componentsFactory.createComponent(VBoxLayout)

                label.value = entity.result.toString()
                if (entity.result == HealthCheckResultType.SUCCESS) {
                    label.styleName = 'success'
                }
                else {
                    label.styleName = 'failure'
                }

                label.width = "120px"
                layout.spacing = true
                layout.add(label)
                return layout
            }
        })
//        healthCheckRunsTable.addColumn(new Table.Column(healthCheckRunsDs.metaClass.getPropertyPath('executedAt')))
//        healthCheckRunsTable.addColumn(new Table.Column(healthCheckRunsDs.metaClass.getPropertyPath('summary')))


    }

    void runHealthChecks() {
        healthCheckService.runHealthChecks()

        healthCheckRunsDs.refresh()


    }

}