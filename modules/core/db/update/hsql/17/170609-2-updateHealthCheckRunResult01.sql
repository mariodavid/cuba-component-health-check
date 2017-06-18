alter table DDCHC_HEALTH_CHECK_REPORT_DETAIL add column MESSAGE varchar(255) ;
alter table DDCHC_HEALTH_CHECK_REPORT_DETAIL add column DETAILED_MESSAGE varchar(4000) ;
alter table DDCHC_HEALTH_CHECK_REPORT_DETAIL drop column SUMMARY cascade ;
