package com.mamay.dao;

import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.DaoException;

import java.util.List;

/**
 * interacts with table news
 */
public interface NewsDao extends CommonDao<Long, NewsEntity> {

    NewsPageItem<NewsEntity> loadByFilter(NewsSearchCriteria filteredItem, Integer pageNumber, int newsPerPage)
            throws DaoException;

    Long loadNextId(NewsSearchCriteria filteredItem, Long id) throws DaoException;

    Long loadPreviousId(NewsSearchCriteria filteredItem, Long id) throws DaoException;

    /**
     * @param newsIdList - array of unique news identifiers
     * @throws DaoException - when there is a connection error
     */
    void deleteList(List<Long> newsIdList) throws DaoException;

    //TODO implement or remove these methods
    default void attachTags(Long newsId, List<Long> tagId) throws DaoException {
        throw new DaoException("ERROR");
    }

    default void attachAuthor(Long newsId, Long authorId) throws DaoException {
        throw new DaoException("ERROR");
    }

    default void updateAuthor(Long newsId, Long authorId) throws DaoException {
        throw new DaoException("ERROR");
    }

    default void detachTags(Long newsId) throws DaoException {
        throw new DaoException("ERROR");
    }
    // TODO implement or remove these methods
}