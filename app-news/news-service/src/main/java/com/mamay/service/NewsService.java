package com.mamay.service;

import com.mamay.dao.NewsDao;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.DaoException;
import com.mamay.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service("newsService")
public class NewsService {
    @Autowired
    private NewsDao newsDao;

    public List<NewsEntity> loadAll() throws ServiceException {
        try {
            return newsDao.loadAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public NewsEntity loadById(Long id) throws ServiceException {
        try {
            return newsDao.loadById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    public Long loadNextId(NewsSearchCriteria filteredItem, Long id) throws ServiceException {
        try {
            return newsDao.loadNextId(filteredItem, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Long loadPreviousId(NewsSearchCriteria filteredItem, Long id) throws ServiceException {
        try {
            return newsDao.loadPreviousId(filteredItem, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void attachTags(Long newsId, List<Long> tagId) throws ServiceException {
        try {
            if (tagId == null || tagId.isEmpty()) {
                return;
            }
            tagId.removeAll(Collections.singleton(null));
            newsDao.attachTags(newsId, tagId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void attachAuthor(Long newsId, Long authorId) throws ServiceException {
        try {
            newsDao.attachAuthor(newsId, authorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Long create(NewsEntity entity) throws ServiceException {
        try {
            return newsDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void update(NewsEntity entity) throws ServiceException {
        try {
            newsDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(Long id) throws ServiceException {
        try {
            newsDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public void deleteList(List<Long> newsIdList) throws ServiceException {
        if (newsIdList == null) {
            throw new ServiceException("No one news was selected to delete!");
        }
        try {
            newsDao.deleteList(newsIdList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void detachTags(Long newsId) throws ServiceException {
        try {
            newsDao.detachTags(newsId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateAuthor(Long newsId, Long authorId) throws ServiceException {
        try {
            newsDao.updateAuthor(newsId, authorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public NewsPageItem<NewsEntity> loadByFilter(NewsSearchCriteria filteredItem, Integer pageNumber, int newsPerPage)
            throws ServiceException {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        try {
            return newsDao.loadByFilter(filteredItem, pageNumber, newsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
