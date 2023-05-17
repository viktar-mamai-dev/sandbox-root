package com.mamay.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.mamay.TestHolder;
import com.mamay.dao.CommentDao;
import com.mamay.entity.CommentEntity;
import com.mamay.exception.NewsException;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

  @InjectMocks private CommentService commentService;

  @Mock private CommentDao commentDao;

  @Test
  public void loadAll() {
    Mockito.doReturn(TestHolder.generateCommentList()).when(commentDao).loadAll();
    List<CommentEntity> actualList = commentService.loadAll();
    assertEquals(3, actualList.size());
  }

  @Test
  public void loadById() {
    Mockito.doReturn(null).when(commentDao).loadById(100L);
    CommentEntity entity = commentService.loadById(100L);
    assertNull(entity);
  }

  @Test
  public void create() {
    CommentEntity entity = TestHolder.generateComment();
    commentService.create(entity);
    entity = TestHolder.generateComment();
    commentService.create(entity);

    Mockito.verify(commentDao, Mockito.times(2)).create(Matchers.any(CommentEntity.class));
  }

  @Test
  public void update() {
    CommentEntity entity = TestHolder.generateComment();
    commentService.update(entity);
    Mockito.verify(commentDao).update(entity);
  }

  @Test
  public void delete() {
    commentService.delete(2L);
    commentService.delete(6L);

    Mockito.verify(commentDao, Mockito.atLeast(2)).delete(Matchers.anyLong());
  }

  @Test(expected = NewsException.class)
  public void deleteFail() {
    Mockito.doThrow(NewsException.class).when(commentDao).delete(Matchers.anyLong());
    commentService.delete(50L);
    commentService.delete(100L);
  }

  @Test(expected = NewsException.class)
  public void loadByIdFail() {
    Mockito.doThrow(NewsException.class).when(commentDao).loadById(Matchers.anyLong());
    commentService.loadById(2L);
  }
}
