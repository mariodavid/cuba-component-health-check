-- alter table DDCHC_HEALTH_CHECK_RUN_RESULT add column HEALTH_CHECK_RUN_ID varchar(36) ^
-- update DDCHC_HEALTH_CHECK_RUN_RESULT set HEALTH_CHECK_RUN_ID = <default_value> ;
-- alter table DDCHC_HEALTH_CHECK_RUN_RESULT alter column HEALTH_CHECK_RUN_ID set not null ;
alter table DDCHC_HEALTH_CHECK_RUN_RESULT add column HEALTH_CHECK_RUN_ID varchar(36) not null ;
