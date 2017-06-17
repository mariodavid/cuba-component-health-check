alter table DDCHC_HEALTH_CHECK_RUN_RESULT add column MESSAGE varchar(255) ;
alter table DDCHC_HEALTH_CHECK_RUN_RESULT add column DETAILED_MESSAGE varchar(4000) ;
alter table DDCHC_HEALTH_CHECK_RUN_RESULT drop column SUMMARY cascade ;
