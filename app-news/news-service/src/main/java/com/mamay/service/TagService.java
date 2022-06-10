package com.mamay.service;

import com.mamay.dao.TagDao;
import com.mamay.entity.TagEntity;
import com.mamay.exception.DaoException;
import com.mamay.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagService")
public class TagService {

    @Autowired
    private TagDao tagDao;

    public List<TagEntity> loadAll() throws ServiceException {
        try {
            return tagDao.loadAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public TagEntity loadById(Long id) throws ServiceException {
        try {
            return tagDao.loadById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<TagEntity> loadByNewsId(Long newsId) throws ServiceException {
        try {
            return tagDao.loadByNewsId(newsId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Long create(TagEntity entity) throws ServiceException {
        try {
            return tagDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void update(TagEntity entity) throws ServiceException {
        try {
            tagDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(Long tagId) throws ServiceException {
        try {
            tagDao.delete(tagId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
