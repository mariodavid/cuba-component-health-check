package de.diedavids.cuba.healthcheck.web.initialcheck

import de.diedavids.cuba.healthcheck.entity.HealthCheckConfiguration
import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.components.actions.EditAction
import com.haulmont.cuba.gui.data.CollectionDatasource
import com.haulmont.cuba.gui.data.DataSupplier
import com.haulmont.cuba.gui.data.Datasource
import com.haulmont.cuba.security.entity.EntityOp

import javax.inject.Inject

class HealthCheckConfigurationBrowse extends AbstractLookup {

    /**
     * The {@link CollectionDatasource} instance that loads a list of {@link HealthCheckConfiguration} records
     * to be displayed in {@link HealthCheckConfigurationBrowse#healthCheckConfigurationsTable} on the left
     */
    @Inject
    private CollectionDatasource<HealthCheckConfiguration, UUID> healthCheckConfigurationsDs

    /**
     * The {@link Datasource} instance that contains an instance of the selected entity
     * in {@link HealthCheckConfigurationBrowse#healthCheckConfigurationsDs}
     * <p/> Containing instance is loaded in {@link CollectionDatasource#addItemChangeListener}
     * with the view, specified in the XML screen descriptor.
     * The listener is set in the {@link HealthCheckConfigurationBrowse#init(Map)} method
     */
    @Inject
    private Datasource<HealthCheckConfiguration> healthCheckConfigurationDs

    /**
     * The {@link Table} instance, containing a list of {@link HealthCheckConfiguration} records,
     * loaded via {@link HealthCheckConfigurationBrowse#healthCheckConfigurationsDs}
     */
    @Inject
    private GroupTable<HealthCheckConfiguration> healthCheckConfigurationsTable

    /**
     * The {@link BoxLayout} instance that contains components on the left side
     * of {@link SplitPanel}
     */
    @Inject
    private BoxLayout lookupBox

    /**
     * The {@link BoxLayout} instance that contains buttons to invoke Save or Cancel actions in edit mode
     */
    @Inject
    private BoxLayout actionsPane

    /**
     * The {@link FieldGroup} instance that is linked to {@link HealthCheckConfigurationBrowse#healthCheckConfigurationDs}
     * and shows fields of the selected {@link HealthCheckConfiguration} record
     */
    @Inject
    private FieldGroup fieldGroup
    
    @Inject
    private DataSupplier dataSupplier

    /**
     * {@link Boolean} value, indicating if a new instance of {@link HealthCheckConfiguration} is being created
     */
    private boolean creating;

    @Override
    public void init(Map<String, Object> params) {
        /*
         * Adding {@link com.haulmont.cuba.gui.data.Datasource.ItemChangeListener} to {@link healthCheckConfigurationsDs}
         * The listener reloads the selected record with the specified view and sets it to {@link healthCheckConfigurationDs}
         */
        healthCheckConfigurationsDs.addItemChangeListener({def e ->
            if (e.getItem() != null) {
                HealthCheckConfiguration reloadedItem = dataSupplier.reload(e.getDs().getItem(), healthCheckConfigurationDs.getView())
                healthCheckConfigurationDs.setItem(reloadedItem)
            }
        })
        

        /*
         * Adding {@link EditAction} to {@link healthCheckConfigurationsTable}
         * The listener enables controls for record editing
         */
        healthCheckConfigurationsTable.addAction(new EditAction(healthCheckConfigurationsTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity existingItem, Datasource parentDs, Map<String, Object> openParams) {
                if (healthCheckConfigurationsTable.getSelected().size() == 1) {
                    refreshOptionsForLookupFields()
                    enableEditControls(false)
                }
            }

            @Override
            public void refreshState() {
                if (target != null) {
                    CollectionDatasource ds = target.getDatasource()
                    if (ds != null && !captionInitialized) {
                        setCaption(messages.getMainMessage("actions.Edit"))
                    }
                }
                super.refreshState()
            }

            @Override
            protected boolean isPermitted() {
                CollectionDatasource ownerDatasource = target.getDatasource()
                boolean entityOpPermitted = security.isEntityOpPermitted(ownerDatasource.getMetaClass(), EntityOp.UPDATE)
                if (!entityOpPermitted) {
                    return false
                }
                return super.isPermitted()
            }
        })
        
        disableEditControls()
    }

    private void refreshOptionsForLookupFields() {
        for (Component component : fieldGroup.getOwnComponents()) {
            if (component instanceof LookupField) {
                CollectionDatasource optionsDatasource = ((LookupField) component).getOptionsDatasource()
                if (optionsDatasource != null) {
                    optionsDatasource.refresh()
                }
            }
        }
    }

    @Override
    void ready() {
        super.ready()

        healthCheckConfigurationsTable.expandAll()
    }
/**
     * Method that is invoked by clicking Save button after editing an existing or creating a new record
     */
    public void save() {
        if (!validate(Collections.singletonList(fieldGroup))) {
            return
        }
        getDsContext().commit()

        HealthCheckConfiguration editedItem = healthCheckConfigurationDs.getItem()
        if (creating) {
            healthCheckConfigurationsDs.includeItem(editedItem)
        } else {
            healthCheckConfigurationsDs.updateItem(editedItem)
        }
        healthCheckConfigurationsTable.setSelected(editedItem)

        disableEditControls()
    }

    /**
     * Method that is invoked by clicking Save button after editing an existing or creating a new record
     */
    public void cancel() {
        HealthCheckConfiguration selectedItem = healthCheckConfigurationsDs.getItem()
        if (selectedItem != null) {
            HealthCheckConfiguration reloadedItem = dataSupplier.reload(selectedItem, healthCheckConfigurationDs.getView())
            healthCheckConfigurationsDs.setItem(reloadedItem)
        } else {
            healthCheckConfigurationDs.setItem(null)
        }

        disableEditControls()
    }

    /**
     * Enabling controls for record editing
     * @param creating indicates if a new instance of {@link HealthCheckConfiguration} is being created
     */
    private void enableEditControls(boolean creating) {
        this.creating = creating
        initEditComponents(true)
        fieldGroup.requestFocus()
    }

    /**
     * Disabling editing controls
     */
    private void disableEditControls() {
        initEditComponents(false)
        healthCheckConfigurationsTable.requestFocus()
    }

    /**
     * Initiating edit controls, depending on if they should be enabled/disabled
     * @param enabled if true - enables editing controls and disables controls on the left side of the splitter
     *                if false - visa versa
     */
    private void initEditComponents(boolean enabled) {
        fieldGroup.setEditable(enabled)
        actionsPane.setVisible(enabled)
        lookupBox.setEnabled(!enabled)
    }
}