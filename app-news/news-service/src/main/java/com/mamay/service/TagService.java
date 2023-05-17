package com.mamay.service;

import com.mamay.dao.TagDao;
import com.mamay.entity.TagEntity;
import com.mamay.exception.NewsException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

  @Autowired private TagDao tagDao;

  public List<TagEntity> loadAll() throws NewsException {
    return tagDao.loadAll();
  }

  public TagEntity loadById(Long id) throws NewsException {
    return tagDao.loadById(id);
  }

  public List<TagEntity> loadByNewsId(Long newsId) throws NewsException {
    return tagDao.loadByNewsId(newsId);
  }

  public Long create(TagEntity entity) throws NewsException {
    return tagDao.create(entity);
  }

  public void update(TagEntity entity) throws NewsException {
    tagDao.update(entity);
  }

  public void delete(Long tagId) throws NewsException {
    tagDao.delete(tagId);
  }
}
