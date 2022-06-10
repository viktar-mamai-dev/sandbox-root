package com.mamay.dao;

import com.mamay.exception.DaoException;

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
     * @throws DaoException when there is connection error
     */
    List<T> loadAll() throws DaoException;

    /**
     * @param id - unique entity identifier
     * @return - entity from table if id exists
     * @throws DaoException when entity is not exist
     */
    T loadById(K id) throws DaoException;

    /**
     * @param entity - to be created
     * @return entity id
     * @throws DaoException when entity was not found
     */
    K create(T entity) throws DaoException;

    /**
     * @param entity - to be updated
     * @throws DaoException when entity was not found
     */
    void update(T entity) throws DaoException;

    /**
     * @param id - unique entity identifier
     * @throws DaoException when no id found
     */
    void delete(K id) throws DaoException;
}
