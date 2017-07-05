-- begin DDCHC_HC_REPORT
create table DDCHC_HC_REPORT (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50),
    --
    EXECUTED_AT timestamp not null,
    RESULT_ varchar2(50) not null,
    SUMMARY varchar2(4000),
    INITIAL_CHECK char(1),
    --
    primary key (ID)
)^
-- end DDCHC_HC_REPORT
-- begin DDCHC_HC_REPORT_DETAIL
create table DDCHC_HC_REPORT_DETAIL (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50),
    --
    RESULT_ varchar2(50) not null,
    MESSAGE varchar2(255),
    DETAILED_MESSAGE clob,
    HEALTH_CHECK_RUN_ID varchar2(32) not null,
    CONFIGURATION_ID varchar2(32) not null,
    --
    primary key (ID)
)^
-- end DDCHC_HC_REPORT_DETAIL
-- begin DDCHC_HC_CONFIGURATION
create table DDCHC_HC_CONFIGURATION (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50),
    DTYPE varchar2(31),
    --
    TYPE_ varchar2(50) not null,
    ACTIVE char(1),
    NAME varchar2(255) not null,
    CODE varchar2(255),
    DESCRIPTION varchar2(4000),
    SOLUTION_INFORMATION varchar2(4000),
    CATEGORY_ID varchar2(32),
    INITIAL_ char(1),
    --
    -- from ddchc$CustomHealthCheckConfiguration
    SCRIPT clob,
    --
    primary key (ID)
)^
-- end DDCHC_HC_CONFIGURATION
-- begin DDCHC_HC_CATEGORY
create table DDCHC_HC_CATEGORY (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50),
    --
    NAME varchar2(255) not null,
    --
    primary key (ID)
)^
-- end DDCHC_HC_CATEGORY
