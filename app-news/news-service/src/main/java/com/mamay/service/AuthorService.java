package com.mamay.service;

import com.mamay.dao.AuthorDao;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.NewsException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  @Autowired private AuthorDao authorDao;

  public List<AuthorEntity> loadAll() {
    return authorDao.loadAll();
  }

  public List<AuthorEntity> loadActiveAuthors() {
    return authorDao.loadActiveAuthors();
  }

  public AuthorEntity loadById(Long id) {
    return authorDao.loadById(id);
  }

  public AuthorEntity loadByNewsId(Long newsId) throws NewsException {
    try {
      return authorDao.loadByNewsId(newsId);
    } catch (NewsException e) {
      throw new NewsException(e);
    }
  }

  public Long create(AuthorEntity entity) {
    return authorDao.create(entity);
  }

  public void update(AuthorEntity entity) {
    authorDao.update(entity);
  }

  public void delete(Long authorId) {
    authorDao.delete(authorId);
  }

  public void makeExpired(Long authorId) {
    authorDao.makeExpired(authorId);
  }
}
