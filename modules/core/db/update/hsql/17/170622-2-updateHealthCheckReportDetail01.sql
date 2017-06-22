-- alter table DDCHC_HEALTH_CHECK_REPORT_DETAIL add column CONFIGURATION_ID varchar(36) ^
-- update DDCHC_HEALTH_CHECK_REPORT_DETAIL set CONFIGURATION_ID = <default_value> ;
-- alter table DDCHC_HEALTH_CHECK_REPORT_DETAIL alter column CONFIGURATION_ID set not null ;
alter table DDCHC_HEALTH_CHECK_REPORT_DETAIL add column CONFIGURATION_ID varchar(36) not null ;
