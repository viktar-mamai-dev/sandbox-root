package com.mamay.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mamay.TestHolder;
import com.mamay.entity.CommentEntity;
import com.mamay.exception.DaoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
@TestExecutionListeners(listeners = {DbUnitTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@DatabaseSetup(value = "/dbunit/comment-data.xml")
public class CommentDaoImplTest {

    @Autowired
    private CommentDao commentDao;

    @Test
    public void loadAll() throws DaoException {
        List<CommentEntity> commentList = commentDao.loadAll();
        assertEquals(20, commentList.size());
    }

    @Test
    public void loadByNewsId() throws DaoException {
        List<CommentEntity> commentList = commentDao.loadByNewsId(Long.valueOf(11));
        assertEquals(3, commentList.size());
    }

    @Test
    public void loadById() throws DaoException {
        CommentEntity actualEntity = commentDao.loadById(Long.valueOf(5));
        assertNotNull(actualEntity);
    }

    @Test
    public void create() throws DaoException {
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
    public void update() throws DaoException {
        CommentEntity entity = TestHolder.generateComment();
        entity.setId(11L);
        commentDao.update(entity);
        CommentEntity actualEntity = commentDao.loadById(11L);
        assertEquals(entity, actualEntity);
    }

    @Test
    public void delete() throws DaoException {
        commentDao.delete(Long.valueOf(11));
        commentDao.delete(Long.valueOf(14));
        List<CommentEntity> commentList = commentDao.loadAll();
        assertEquals(18, commentList.size());
    }
}
