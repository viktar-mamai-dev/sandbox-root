package com.mamay.dao;

import com.mamay.exception.NewsException;

import java.util.List;

/**
 * @param <K> - type of unique id
 * @param <T> - type of entity to be persisted
 * @author Viktar_Mamai
 * <p>
 * abstract basic dao, that defines main operations for all derived dao
 */
public interface CommonDao<K, T> {

    /**
     * @return full list of entities at appropriate table in db
     * @throws NewsException when there is connection error
     */
    List<T> loadAll() throws NewsException;

    /**
     * @param id - unique entity identifier
     * @return - entity from table if id exists
     * @throws NewsException when entity is not exist
     */
    T loadById(K id) throws NewsException;

    /**
     * @param entity - to be created
     * @return entity id
     * @throws NewsException when entity was not found
     */
    K create(T entity) throws NewsException;

    /**
     * @param entity - to be updated
     * @throws NewsException when entity was not found
     */
    void update(T entity) throws NewsException;

    /**
     * @param id - unique entity identifier
     * @throws NewsException when no id found
     */
    void delete(K id) throws NewsException;
}
