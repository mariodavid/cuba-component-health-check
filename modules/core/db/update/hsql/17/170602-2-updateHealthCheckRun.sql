alter table DDCHC_HEALTH_CHECK_REPORT add column EXECUTED_AT timestamp ^
update DDCHC_HEALTH_CHECK_REPORT set EXECUTED_AT = current_timestamp where EXECUTED_AT is null ;
alter table DDCHC_HEALTH_CHECK_REPORT alter column EXECUTED_AT set not null ;
alter table DDCHC_HEALTH_CHECK_REPORT drop column TIMESTAMP_ cascade ;
