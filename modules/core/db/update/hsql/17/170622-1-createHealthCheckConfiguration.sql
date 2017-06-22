create table DDCHC_HEALTH_CHECK_CONFIGURATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ACTIVE boolean,
    HEALTH_CHECK_CLASS varchar(4000) not null,
    --
    primary key (ID)
);
