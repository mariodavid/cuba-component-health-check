alter table DDCHC_HEALTH_CHECK_RUN add column SUMMARY varchar(255) ;
alter table DDCHC_HEALTH_CHECK_RUN drop column MESSAGE cascade ;
