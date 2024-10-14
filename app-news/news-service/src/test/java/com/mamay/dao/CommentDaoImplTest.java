package com.mamay.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mamay.TestHolder;
import com.mamay.entity.CommentEntity;
import com.mamay.exception.NewsException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
@Sql(scripts = "data/comment-data.sql")
public class CommentDaoImplTest {

  @Autowired private CommentDao commentDao;

  @Test
  public void loadAll() throws NewsException {
    List<CommentEntity> commentList = commentDao.loadAll();
    Assertions.assertEquals(20, commentList.size());
  }

  @Test
  public void loadByNewsId() throws NewsException {
    List<CommentEntity> commentList = commentDao.loadByNewsId(Long.valueOf(11));
    assertEquals(3, commentList.size());
  }

  @Test
  public void loadById() throws NewsException {
    CommentEntity actualEntity = commentDao.loadById(Long.valueOf(5));
    assertNotNull(actualEntity);
  }

  @Test
  public void create() throws NewsException {
    CommentEntity entity = TestHolder.generateComment();
    Long id = commentDao.create(entity);
    assertNotNull(id);
    entity.setId(id);
    entity.setText("not aware of changes");
    commentDao.create(entity);
    List<CommentEntity> actualList = commentDao.loadAll();
    assertEquals(22, actualList.size());
  }

  @Test
  public void update() throws NewsException {
    CommentEntity entity = TestHolder.generateComment();
    entity.setId(11L);
    commentDao.update(entity);
    CommentEntity actualEntity = commentDao.loadById(11L);
    assertEquals(entity, actualEntity);
  }

  @Test
  public void delete() throws NewsException {
    commentDao.delete(11L);
    commentDao.delete(14L);
    List<CommentEntity> commentList = commentDao.loadAll();
    assertEquals(18, commentList.size());
  }
}
