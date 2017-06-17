alter table DDCHC_HEALTH_CHECK_RUN add column MESSAGE varchar(255) ;
alter table DDCHC_HEALTH_CHECK_RUN add column DETAILED_MESSAGE varchar(400) ;
alter table DDCHC_HEALTH_CHECK_RUN drop column SUMMARY cascade ;
