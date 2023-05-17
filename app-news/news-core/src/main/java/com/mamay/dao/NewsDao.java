package com.mamay.dao;

import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import java.util.List;

/** interacts with table news */
public interface NewsDao extends CommonDao<Long, NewsEntity> {

  NewsPageItem<NewsEntity> loadByFilter(
      NewsSearchCriteria filteredItem, Integer pageNumber, int newsPerPage) throws NewsException;

  Long loadNextId(NewsSearchCriteria filteredItem, Long id) throws NewsException;

  Long loadPreviousId(NewsSearchCriteria filteredItem, Long id) throws NewsException;

  /**
   * @param newsIdList - array of unique news identifiers
   * @throws NewsException - when there is a connection error
   */
  void deleteList(List<Long> newsIdList) throws NewsException;

  // TODO implement or remove these methods
  default void attachTags(Long newsId, List<Long> tagId) throws NewsException {
    throw new NewsException("ERROR");
  }

  default void attachAuthor(Long newsId, Long authorId) throws NewsException {
    throw new NewsException("ERROR");
  }

  default void updateAuthor(Long newsId, Long authorId) throws NewsException {
    throw new NewsException("ERROR");
  }

  default void detachTags(Long newsId) throws NewsException {
    throw new NewsException("ERROR");
  }
  // TODO implement or remove these methods
}
