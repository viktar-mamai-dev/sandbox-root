package com.mamay.dao;

import com.mamay.entity.TagEntity;
import com.mamay.exception.NewsException;
import java.util.List;

/** interacts with table tag */
public interface TagDao extends CommonDao<Long, TagEntity> {
  /**
   * @param newsId - unique news identifier
   * @return - list of tags each of one has such newsId
   * @throws NewsException - if there is any connection error
   */
  List<TagEntity> loadByNewsId(Long newsId) throws NewsException;
}
