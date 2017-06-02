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
    TIMESTAMP_ date not null,
    SUMMARY varchar(4000),
    RESULT_ varchar(50) not null,
    --
    primary key (ID)
);
