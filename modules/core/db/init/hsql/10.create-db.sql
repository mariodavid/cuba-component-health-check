-- begin DDCHC_HEALTH_CHECK_REPORT
create table DDCHC_HEALTH_CHECK_REPORT (
    ID varchar(36) not null,
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
    SUMMARY varchar(255),
    DETAILED_MESSAGE varchar(400),
    --
    primary key (ID)
)^
-- end DDCHC_HEALTH_CHECK_REPORT
-- begin DDCHC_HEALTH_CHECK_REPORT_DETAIL
create table DDCHC_HEALTH_CHECK_REPORT_DETAIL (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    RESULT_ varchar(50) not null,
    CATEGORY varchar(255),
    MESSAGE varchar(255),
    DETAILED_MESSAGE varchar(4000),
    HEALTH_CHECK_RUN_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end DDCHC_HEALTH_CHECK_REPORT_DETAIL
