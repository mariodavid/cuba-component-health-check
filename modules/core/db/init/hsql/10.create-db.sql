-- begin DDCHC_HEALTH_CHECK_RUN
create table DDCHC_HEALTH_CHECK_RUN (
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
    SUMMARY varchar(4000),
    RESULT_ varchar(50) not null,
    --
    primary key (ID)
)^
-- end DDCHC_HEALTH_CHECK_RUN
-- begin DDCHC_HEALTH_CHECK_RUN_RESULT
create table DDCHC_HEALTH_CHECK_RUN_RESULT (
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
    SUMMARY varchar(255),
    HEALTH_CHECK_RUN_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end DDCHC_HEALTH_CHECK_RUN_RESULT
