package com.mamay.service;

import com.mamay.TestHolder;
import com.mamay.dao.CommentDao;
import com.mamay.entity.CommentEntity;
import com.mamay.exception.DaoException;
import com.mamay.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentDao commentDao;

    @Test
    public void loadAll() throws ServiceException, DaoException {
        Mockito.doReturn(TestHolder.generateCommentList()).when(commentDao).loadAll();
        List<CommentEntity> actualList = commentService.loadAll();
        assertEquals(3, actualList.size());
    }

    @Test
    public void loadById() throws ServiceException, DaoException {
        Mockito.doReturn(null).when(commentDao).loadById(Long.valueOf(100));
        CommentEntity entity = commentService.loadById(Long.valueOf(100));
        assertNull(entity);
    }

    @Test
    public void create() throws ServiceException, DaoException {
        CommentEntity entity = TestHolder.generateComment();
        commentService.create(entity);
        entity = TestHolder.generateComment();
        commentService.create(entity);

        Mockito.verify(commentDao, Mockito.times(2)).create(Matchers.any(CommentEntity.class));
    }

    @Test
    public void update() throws ServiceException, DaoException {
        CommentEntity entity = TestHolder.generateComment();
        commentService.update(entity);
        Mockito.verify(commentDao).update(entity);
    }

    @Test
    public void delete() throws ServiceException, DaoException {
        commentService.delete(Long.valueOf(2));
        commentService.delete(Long.valueOf(6));

        Mockito.verify(commentDao, Mockito.atLeast(2)).delete(Matchers.anyLong());
    }

    @Test(expected = ServiceException.class)
    public void deleteFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(commentDao).delete(Matchers.anyLong());
        commentService.delete(Long.valueOf(50));
        commentService.delete(Long.valueOf(100));
    }

    @Test(expected = ServiceException.class)
    public void loadByIdFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(commentDao).loadById(Matchers.anyLong());
        commentService.loadById(Long.valueOf(2));
    }
}
