alter table DDCHC_HEALTH_CHECK_CONFIGURATION add column TYPE_ varchar(50) ^
update DDCHC_HEALTH_CHECK_CONFIGURATION set TYPE_ = 'SYSTEM' where TYPE_ is null ;
alter table DDCHC_HEALTH_CHECK_CONFIGURATION alter column TYPE_ set not null ;
