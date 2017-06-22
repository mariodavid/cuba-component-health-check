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
);
