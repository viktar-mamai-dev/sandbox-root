package com.mamay.service;

import com.mamay.dao.CommentDao;
import com.mamay.entity.CommentEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  @Autowired private CommentDao commentDao;

  public List<CommentEntity> loadAll() {
    return commentDao.loadAll();
  }

  public CommentEntity loadById(Long id) {
    return commentDao.loadById(id);
  }

  public List<CommentEntity> loadByNewsId(Long newsId) {
    return commentDao.loadByNewsId(newsId);
  }

  public Long create(CommentEntity entity) {
    return commentDao.create(entity);
  }

  public void update(CommentEntity entity) {
    commentDao.update(entity);
  }

  public void delete(Long commentId) {
    commentDao.delete(commentId);
  }
}
