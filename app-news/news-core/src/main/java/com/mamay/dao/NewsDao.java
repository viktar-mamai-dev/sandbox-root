package com.mamay.dao;

import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import java.util.List;

/** interacts with table news */
public interface NewsDao extends CommonDao<Long, NewsEntity> {

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
