alter table DDCHC_HEALTH_CHECK_RUN add column EXECUTED_AT timestamp ^
update DDCHC_HEALTH_CHECK_RUN set EXECUTED_AT = current_timestamp where EXECUTED_AT is null ;
alter table DDCHC_HEALTH_CHECK_RUN alter column EXECUTED_AT set not null ;
alter table DDCHC_HEALTH_CHECK_RUN drop column TIMESTAMP_ cascade ;
