alter table DDCHC_HEALTH_CHECK_REPORT add column MESSAGE varchar(255) ;
alter table DDCHC_HEALTH_CHECK_REPORT add column DETAILED_MESSAGE varchar(400) ;
alter table DDCHC_HEALTH_CHECK_REPORT drop column SUMMARY cascade ;
