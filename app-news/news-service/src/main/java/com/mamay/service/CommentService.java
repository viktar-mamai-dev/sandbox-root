package com.mamay.service;

import com.mamay.dao.CommentDao;
import com.mamay.entity.CommentEntity;
import com.mamay.exception.DaoException;
import com.mamay.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public List<CommentEntity> loadAll() throws ServiceException {
        try {
            return commentDao.loadAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public CommentEntity loadById(Long id) throws ServiceException {
        try {
            return commentDao.loadById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<CommentEntity> loadByNewsId(Long newsId)
            throws ServiceException {
        try {
            return commentDao.loadByNewsId(newsId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Long create(CommentEntity entity) throws ServiceException {
        try {
            return commentDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void update(CommentEntity entity) throws ServiceException {
        try {
            commentDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(Long commentId) throws ServiceException {
        try {
            commentDao.delete(commentId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}