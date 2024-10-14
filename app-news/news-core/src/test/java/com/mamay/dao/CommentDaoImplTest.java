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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
@Sql(value = "/dbunit/comment-data.xml")
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
    commentDao.delete(Long.valueOf(11));
    commentDao.delete(Long.valueOf(14));
    List<CommentEntity> commentList = commentDao.loadAll();
    assertEquals(18, commentList.size());
  }
}
