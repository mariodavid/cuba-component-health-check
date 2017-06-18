package de.diedavids.cuba.healthcheck.core.healthchecks;

import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract superclass for defining a check that executes a shell command and checks if the exit code of
 * the execution is zero
 *
 * This is normally useful if you want to check certain environment settings like
 * - open office / libre office installed for report addon
 * - internet connectivity through ping
 */
public abstract class ShellExecutionHealthCheck extends AbstractHealthCheck {

    private Logger log = LoggerFactory.getLogger(ShellExecutionHealthCheck.class);

    @Override
    public HealthCheckReportDetail check() {
        try {

            Process p = Runtime.getRuntime().exec(getShellCommand());
            p.waitFor();

            if (p.exitValue() == 0) {
                return handleSuccessfulExecution(p);
            } else {
                return handleErrorExecution(p);
            }
        }
        catch (Exception e) {
            log.error("Error while executing shell command (" + e.getMessage() + ")", e);
            return error("Error while executing shell command check", e.getMessage());
        }

    }


    /**
     * defines the shell command that should be executed by the checker
     *
     * @return the shell command
     */
    protected abstract String getShellCommand();


    /**
     * handles the successful execution of the shell command (exit code = 0)
     * @param shellCommandProcess the process instance
     * @return the corresponding health check result created in the success case
     */
    protected abstract HealthCheckReportDetail handleSuccessfulExecution(Process shellCommandProcess);


    /**
     * handles the error execution of the shell command (exit code != 0)
     * @param shellCommandProcess the process instance
     * @return the corresponding health check result created in the error case
     */
    protected abstract HealthCheckReportDetail handleErrorExecution(Process shellCommandProcess);

}
