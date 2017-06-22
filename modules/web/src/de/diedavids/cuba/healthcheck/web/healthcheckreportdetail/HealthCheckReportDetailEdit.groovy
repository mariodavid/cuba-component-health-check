package de.diedavids.cuba.healthcheck.web.healthcheckreportdetail

import com.haulmont.cuba.gui.components.AbstractEditor
import com.haulmont.cuba.gui.components.FieldGroup
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail

import javax.inject.Inject

class HealthCheckReportDetailEdit extends AbstractEditor<HealthCheckReportDetail> {

    @Inject
    FieldGroup fieldGroup

    @Override
    protected void postInit() {

        fieldGroup.getField('result').styleName = item.result.fieldStyleName

    }
}