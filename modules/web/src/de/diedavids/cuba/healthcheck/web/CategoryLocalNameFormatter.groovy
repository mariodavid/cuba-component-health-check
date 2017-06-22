package de.diedavids.cuba.healthcheck.web

import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.Messages
import com.haulmont.cuba.gui.components.Formatter

class CategoryLocalNameFormatter implements Formatter {


    @Override
    String format(Object value) {
        messages.getMainMessage("healthCheck.categories.${value}.label")
    }

    Messages getMessages() {
        AppBeans.get(Messages)
    }

}
