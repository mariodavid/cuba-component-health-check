alter table DDCHC_HEALTH_CHECK_REPORT drop column DETAILED_MESSAGE cascade ;
alter table DDCHC_HEALTH_CHECK_REPORT alter column SUMMARY set data type varchar(4000) ;
