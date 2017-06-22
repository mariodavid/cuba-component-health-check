package de.diedavids.cuba.healthcheck.data;

import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.Collection;
import java.util.UUID;

public interface SimpleDataLoader {


    String NAME = "ddchc_SimpleDataLoader";

    <E extends BaseUuidEntity> E load(Class<E> entityClass, UUID id);
    <E extends BaseUuidEntity> E load(Class<E> entityClass, UUID id, String view);

    <E extends BaseUuidEntity> E loadByProperty(Class<E> entityClass, String propertyPath, String propertyValue);
    <E extends BaseUuidEntity> E loadByProperty(Class<E> entityClass, String propertyPath, String propertyValue, String view);

    <E extends BaseUuidEntity> E loadByReferenz(Class<E> entityClass, String propertyPath, BaseUuidEntity referenz);
    <E extends BaseUuidEntity> E loadByReferenz(Class<E> entityClass, String propertyPath, BaseUuidEntity referenz, String view);

    <E extends BaseUuidEntity> Collection<E> loadAll(Class<E> entityClass);
    <E extends BaseUuidEntity> Collection<E> loadAll(Class<E> entityClass, String view);
}
