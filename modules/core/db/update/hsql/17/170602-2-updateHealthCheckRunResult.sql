-- alter table DDCHC_HEALTH_CHECK_REPORT_DETAIL add column HEALTH_CHECK_RUN_ID varchar(36) ^
-- update DDCHC_HEALTH_CHECK_REPORT_DETAIL set HEALTH_CHECK_RUN_ID = <default_value> ;
-- alter table DDCHC_HEALTH_CHECK_REPORT_DETAIL alter column HEALTH_CHECK_RUN_ID set not null ;
alter table DDCHC_HEALTH_CHECK_REPORT_DETAIL add column HEALTH_CHECK_RUN_ID varchar(36) not null ;
