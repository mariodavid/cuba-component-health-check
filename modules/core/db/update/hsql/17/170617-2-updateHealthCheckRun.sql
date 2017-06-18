alter table DDCHC_HEALTH_CHECK_REPORT add column SUMMARY varchar(255) ;
alter table DDCHC_HEALTH_CHECK_REPORT drop column MESSAGE cascade ;
