package com.mamay.dao;

import com.mamay.entity.CommentEntity;
import com.mamay.exception.DaoException;

import java.util.List;

/**
 * interacts with table comments
 */
public interface CommentDao extends CommonDao<Long, CommentEntity> {
    /**
     * @param newsId - unique news identifier
     * @return - list of comments each of one has such newsId
     * @throws DaoException - if there is any connection error
     */
    List<CommentEntity> loadByNewsId(Long newsId) throws DaoException;
}
