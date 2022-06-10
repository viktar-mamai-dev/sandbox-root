package com.mamay.dao;

import com.mamay.entity.AuthorEntity;
import com.mamay.exception.DaoException;

import java.util.List;

/**
 * interacts with table author
 */
public interface AuthorDao extends CommonDao<Long, AuthorEntity> {

    /**
     * @param newsId - unique news identifier
     * @return - author which has such news entity
     * @throws DaoException - when SQLException occurres
     */
    AuthorEntity loadByNewsId(Long newsId) throws DaoException;

    /**
     * @param authorId - unique author identifier
     * @throws DaoException - when there is connection error
     */
    void makeExpired(Long authorId) throws DaoException;

    /**
     * @return - all authors with expired date is null
     * @throws DaoException - when there is connection error
     */
    List<AuthorEntity> loadActiveAuthors() throws DaoException;
}
