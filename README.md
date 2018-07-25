[ ![Download](https://api.bintray.com/packages/mariodavid/cuba-components/cuba-component-health-check/images/download.svg) ](https://bintray.com/mariodavid/cuba-components/cuba-component-health-check/_latestVersion)
[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://travis-ci.org/mariodavid/cuba-component-health-check.svg?branch=master)](https://travis-ci.org/mariodavid/cuba-component-health-check)

# CUBA Platform Component - Health Check

This application component automatically checks your [CUBA](https://www.cuba-platform.com/) application if it is considered "healthy".
It will show the results of the health check to the administrator of the application with information on what went wrong and information
on how to solve the issue.

This helps the administrator of your software in two ways:

1. installing and configuring the software as you (as a developer) intended
2. make problems with the running software visible to the administrator

You can define your own health checks at development time or at runtime that will the state of the application. Here are some examples of this checks:

* Is a dependent service alive and able to take requests
* Does the database contain a Report you created and need in the app
* Do the custom filters stored in the database fit to the entity model
* Have all database changelog scripts been applied successfully


## Installation

1. `health-check` is available in the [CUBA marketplace](https://www.cuba-platform.com/marketplace)
2. Select a version of the add-on which is compatible with the platform version used in your project:

| Platform Version | Add-on Version |
| ---------------- | -------------- |
| 6.8.x            | 0.4.x          |
| 6.7.x            | 0.3.x          |
| 6.6.x            | 0.2.x          |
| 6.5.x            | 0.1.x          |

The latest version is: [ ![Download](https://api.bintray.com/packages/mariodavid/cuba-components/cuba-component-health-check/images/download.svg) ](https://bintray.com/mariodavid/cuba-components/cuba-component-health-check/_latestVersion)

Add custom application component to your project:

* Artifact group: `de.diedavids.cuba.healthcheck`
* Artifact name: `health-check-global`
* Version: *add-on version*

```groovy
dependencies {
  appComponent("de.diedavids.cuba.healthcheck:health-check-global:*addon-version*")
}
```



## Supported DBMS

The following databases are supported by this application component:

* HSQLDB
* PostgreSQL
* MySQL
* Oracle

## Health check overview

You can see the result of the latest health check from `Administration` > `Health Check` > `Latest health check`.

Additionally you get an indicator on the upper right of the application, that will display the result of the latest health check.

![Screenshot health check overview](https://github.com/mariodavid/cuba-component-health-check/blob/master/img/health-check-overview.png)


### Running health checks

There are two options to run the health checks: manually or on a scheduled basis. 

To run the health check manually through the UI, you can use the button "rerun health check" (`Administration` >  `Health Check` > `Latest health check`). 
This option is useful when you trying to fix an issue reported by the health check to see if you really resolved it.
  
The second option is that the health checks are run as a scheduled task. This is the preferred way, because the health check
is meant to be executeed proactively a self-test that will lead to better uptime due to fast and easy access to problems.

In this case, you create a scheduled task through the mechanism of [CUBA scheduled tasks](https://doc.cuba-platform.com/manual-6.5/scheduled_tasks_cuba.html).

The bean executing the health checks: `ddchc_HealthCheckService` with the method `runHealthChecks`. 


![Screenshot creating a health check scheduled task](https://github.com/mariodavid/cuba-component-health-check/blob/master/img/health-check-scheduled-task.png)

How often you let the checks run is up to you and is highly dependent of the type of checks you are planning to implement. 
Generally the idea is to proactively execute these checks and get immediate feedback, if something is not working out,
so more often checks should be preferred.


## Defining health checks

In order to use the system, the developer and or the administrator have to create health checks that will 
execute some logic to determine a particular aspect of the system.

Creating health checks can be done in two ways: at development time and at runtime.

### Development time health checks

For a lot of health checks you already know at development time what you want to check. 
These checks should be defined just as regular Java / Groovy class within your CUBA application.
 
To define custom health checks, you have to create a class that extends [DefaultHealthCheck](https://github.com/mariodavid/cuba-component-health-check/blob/master/modules/core/src/de/diedavids/cuba/healthcheck/core/healthchecks/DefaultHealthCheck.java)
in the core module of your application.


````java
@Component
public class WeatherOfficeHealthCheck extends DefaultHealthCheck {


    @Inject
    WeatherOfficeService weatherOfficeService;
    
    @Override
    public HealthCheckReportDetail check() {
    
        if (weatherOfficeService.isHot()) {
            return error("get outta here, catch a drink and keep calm");
        }
        else if(weatherOfficeService.isWarm()) {
            return warning("You can work on, but make sure you are ready to party");
        }
        else {
            return success("Nothing to see here. Get a coffee...")
        }
    }

    @Override
    protected String getConfigurationCode() {
        return "weather-office-health-check-code";
    }
}
````

In the `check()` method you define the logic that should be checked. The return value is a `HealthCheckReportDetail` object that defines the outcome of the check.
To not deal with the return type directly, you can use the helper methods:

````java
success(String message)
success(String message, String detailedMessage)
warning(String message)
warning(String message, String detailedMessage)
error(String message)
error(String message, String detailedMessage)
````

*Note: Your health check class has to be a Spring bean (`@Component`) in order to get picked up by the check-runtime.*

The method `getConfigurationCode()` returns the `code` of the entity [HealthCheckConfiguration](https://github.com/mariodavid/cuba-component-health-check/blob/master/modules/global/src/de/diedavids/cuba/healthcheck/entity/HealthCheckConfiguration.java) that corresponds with this health check.

There are a few subclasses already available for you that will make defining a health check a little easier:

* `DatabaseEntityInstanceAvailableHealthCheck` - checks for a given instance of an entity to exist in the db
* `ShellExecutionHealthCheck` - executes a shell command and verfies its outcome



### Health check configurations

For every health check in the system, there has to be an database entry in the entity [HealthCheckConfiguration](https://github.com/mariodavid/cuba-component-health-check/blob/master/modules/global/src/de/diedavids/cuba/healthcheck/entity/HealthCheckConfiguration.java).
A HealthCheckConfiguration defines certain aspects of the health check, that are not expressed in the code, because they can be changed by the user:

* `name` - the name is the visual representation of the health check in the UI
* `code` - the code that glues the HealthCheck class to the configuration instance
* `description` - describes the health check in more detail. Will be displayed to the administrator in the health check report.
* `category` - health checks can be grouped into different categories in order to dispay them in the UI accordingly or to run just a subset of the checks 
* `active` - a health check can be activated / deactivated at runtime
* `solution information` - information for the administrator on how to resolve the issue in case the check was not successful

> When you create a health check, you might want to create a corresponding database entry as well at development time. 
> To do so, you can start the application in your development environment and create a database entry via the entity inspector (`Administration` > `Entity inspector` > `Health Check Configuration (ddchc$HealthCheckConfiguration)`)
> To transport these database entries alongside with the application, you can find more information [here](https://www.road-to-cuba-and-beyond.com/test-and-seed-data/).


### Runtime health checks

Oftentimes there is a need to define health checks at runtime. For this use case you can create a `CustomHealthCheckConfiguration` that has a script attached.
To create a runtime health check, go to `Administration > Health Check > Health Check Configurations`.

![Screenshot runtime health checks](https://github.com/mariodavid/cuba-component-health-check/blob/master/img/custom-health-check-configuration.png)


## Inital checks

Besides recurring checks that have to be executed over and over again to make sure the system is still in a good shape, 
there is an additional type of checks: *initial checks*.

The purpose of these checks is to guide the administrator through the initial installation and configuration of the system.

Those checks have to be checks that have been created at development time. The corresponding health check configuration database entry
should set the boolean flag `initial` to true.

If the administrator logs in for the first time, the software will present a dialog window, showing all initial checks, that
haven't been run successfully. 

With this information as well as the corresponding descriptions and solution information
the administrator is able to configure the system so that the state of the running software is just as the
developer intended it to be to work properly.


![Screenshot initial checks overview](https://github.com/mariodavid/cuba-component-health-check/blob/master/img/initial-health-checks-overview.png)

For normal users, that do not have the permission, to see these detailed information about the healthiness
of the system, the application will present the user an error after login saying that the initial configuration
of the software has not been done. This is only true for checks that are of type "initial".

 
