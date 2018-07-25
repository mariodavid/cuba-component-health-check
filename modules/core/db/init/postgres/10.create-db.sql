-- begin DDCHC_HC_REPORT
create table DDCHC_HC_REPORT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    EXECUTED_AT timestamp not null,
    RESULT_ varchar(50) not null,
    SUMMARY varchar(4000),
    INITIAL_CHECK boolean,
    --
    primary key (ID)
)^
-- end DDCHC_HC_REPORT
-- begin DDCHC_HC_REPORT_DETAIL
create table DDCHC_HC_REPORT_DETAIL (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    RESULT_ varchar(50) not null,
    MESSAGE varchar(255),
    DETAILED_MESSAGE text,
    HEALTH_CHECK_RUN_ID uuid not null,
    CONFIGURATION_ID uuid not null,
    --
    primary key (ID)
)^
-- end DDCHC_HC_REPORT_DETAIL
-- begin DDCHC_HC_CONFIGURATION
create table DDCHC_HC_CONFIGURATION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    TYPE_ varchar(50) not null,
    ACTIVE boolean,
    NAME varchar(255) not null,
    CODE varchar(255),
    DESCRIPTION varchar(4000),
    SOLUTION_INFORMATION varchar(4000),
    CATEGORY_ID uuid,
    INITIAL_ boolean,
    --
    -- from ddchc$CustomHealthCheckConfiguration
    SCRIPT text,
    --
    primary key (ID)
)^
-- end DDCHC_HC_CONFIGURATION
-- begin DDCHC_HC_CATEGORY
create table DDCHC_HC_CATEGORY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end DDCHC_HC_CATEGORY
