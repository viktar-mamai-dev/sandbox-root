package com.mamay.service;

import com.mamay.dao.NewsDao;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsDao newsDao;

    public List<NewsEntity> loadAll() throws NewsException {
        return newsDao.loadAll();
    }

    public NewsEntity loadById(Long id) throws NewsException {
        return newsDao.loadById(id);
    }

    public Long loadNextId(NewsSearchCriteria filteredItem, Long id) throws NewsException {
        return newsDao.loadNextId(filteredItem, id);
    }

    public Long loadPreviousId(NewsSearchCriteria filteredItem, Long id) throws NewsException {
        return newsDao.loadPreviousId(filteredItem, id);
    }

    public void attachTags(Long newsId, List<Long> tagId) throws NewsException {
        if (tagId == null || tagId.isEmpty()) {
            return;
        }
        tagId.removeAll(Collections.singleton(null));
        newsDao.attachTags(newsId, tagId);
    }

    public void attachAuthor(Long newsId, Long authorId) throws NewsException {
        newsDao.attachAuthor(newsId, authorId);
    }

    public Long create(NewsEntity entity) throws NewsException {
        return newsDao.create(entity);
    }

    public void update(NewsEntity entity) throws NewsException {
        newsDao.update(entity);
    }

    public void delete(Long id) throws NewsException {
        newsDao.delete(id);
    }

    @Transactional
    public void deleteList(List<Long> newsIdList) throws NewsException {
        if (newsIdList == null) {
            throw new NewsException("No one news was selected to delete!");
        }
        newsDao.deleteList(newsIdList);
    }

    public void detachTags(Long newsId) throws NewsException {
        newsDao.detachTags(newsId);
    }

    public void updateAuthor(Long newsId, Long authorId) throws NewsException {
        newsDao.updateAuthor(newsId, authorId);
    }

    public NewsPageItem<NewsEntity> loadByFilter(NewsSearchCriteria filteredItem, Integer pageNumber, int newsPerPage)
            throws NewsException {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        return newsDao.loadByFilter(filteredItem, pageNumber, newsPerPage);
    }

}
