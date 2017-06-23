update DDCHC_HEALTH_CHECK_CONFIGURATION set NAME = '' where NAME is null ;
alter table DDCHC_HEALTH_CHECK_CONFIGURATION alter column NAME set not null ;
