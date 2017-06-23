package de.diedavids.cuba.healthcheck.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@DiscriminatorValue("CUSTOM")
@Entity(name = "ddchc$CustomHealthCheckConfiguration")
public class CustomHealthCheckConfiguration extends HealthCheckConfiguration {
    private static final long serialVersionUID = 1177382026563700664L;

    @Lob
    @Column(name = "SCRIPT")
    protected String script;

    public void setScript(String script) {
        this.script = script;
    }

    public String getScript() {
        return script;
    }



}