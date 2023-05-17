package com.mamay.dao;

import com.mamay.entity.AuthorEntity;
import com.mamay.exception.NewsException;
import java.util.List;

/** interacts with table author */
public interface AuthorDao extends CommonDao<Long, AuthorEntity> {

  /**
   * @param newsId - unique news identifier
   * @return - author which has such news entity
   * @throws NewsException - when SQLException occurres
   */
  AuthorEntity loadByNewsId(Long newsId) throws NewsException;

  /**
   * @param authorId - unique author identifier
   * @throws NewsException - when there is connection error
   */
  void makeExpired(Long authorId) throws NewsException;

  /**
   * @return - all authors with expired date is null
   * @throws NewsException - when there is connection error
   */
  List<AuthorEntity> loadActiveAuthors() throws NewsException;
}
