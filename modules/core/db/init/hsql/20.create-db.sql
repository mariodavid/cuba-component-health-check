-- begin DDCHC_HEALTH_CHECK_RUN_RESULT
alter table DDCHC_HEALTH_CHECK_RUN_RESULT add constraint FK_DDCHC_HEALTH_CHECK_RUN_RESULT_HEALTH_CHECK_RUN foreign key (HEALTH_CHECK_RUN_ID) references DDCHC_HEALTH_CHECK_RUN(ID)^
create index IDX_DDCHC_HEALTH_CHECK_RUN_RESULT_HEALTH_CHECK_RUN on DDCHC_HEALTH_CHECK_RUN_RESULT (HEALTH_CHECK_RUN_ID)^
-- end DDCHC_HEALTH_CHECK_RUN_RESULT
