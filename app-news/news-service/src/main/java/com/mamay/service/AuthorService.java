package com.mamay.service;

import com.mamay.dao.AuthorDao;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.DaoException;
import com.mamay.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authorService")
public class AuthorService {

    @Autowired
    private AuthorDao authorDao;

    public List<AuthorEntity> loadAll() throws ServiceException {
        try {
            return authorDao.loadAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<AuthorEntity> loadActiveAuthors() throws ServiceException {
        try {
            return authorDao.loadActiveAuthors();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public AuthorEntity loadById(Long id) throws ServiceException {
        try {
            return authorDao.loadById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public AuthorEntity loadByNewsId(Long newsId) throws ServiceException {
        try {
            return authorDao.loadByNewsId(newsId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Long create(AuthorEntity entity) throws ServiceException {
        try {
            return authorDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void update(AuthorEntity entity) throws ServiceException {
        try {
            authorDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(Long authorId) throws ServiceException {
        try {
            authorDao.delete(authorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void makeExpired(Long authorId) throws ServiceException {
        try {
            authorDao.makeExpired(authorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
