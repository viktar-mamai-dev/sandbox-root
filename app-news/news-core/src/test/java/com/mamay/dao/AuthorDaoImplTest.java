package com.mamay.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mamay.TestHolder;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.DaoException;
import org.junit.Assert;
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
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
@TestExecutionListeners(listeners = {DbUnitTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@DatabaseSetup(value = {"/dbunit/author-data.xml", "/dbunit/news_author-data.xml"})
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void loadAll() throws DaoException {
        List<AuthorEntity> authorList = authorDao.loadAll();
        assertEquals(20, authorList.size());
    }

    @Test
    public void loadActiveAuthors() throws DaoException {
        List<AuthorEntity> authorList = authorDao.loadActiveAuthors();
        assertEquals(20, authorList.size());
    }

    @Test
    public void loadByNewsId() throws DaoException {
        AuthorEntity actualEntity = authorDao.loadByNewsId(100L);
        assertNull(actualEntity);
    }

    @Test
    public void loadById() throws DaoException {
        AuthorEntity entity = authorDao.loadById(100L);
        assertNull(entity);
    }

    @Test
    public void create() throws DaoException {
        AuthorEntity entity = TestHolder.generateAuthor();
        authorDao.create(entity);
        entity = TestHolder.generateAuthor();
        authorDao.create(entity);
        List<AuthorEntity> authorList = authorDao.loadAll();
        assertEquals(22, authorList.size());
    }

    @Test
    public void update() throws DaoException {
        AuthorEntity entity = TestHolder.generateAuthor();
        authorDao.update(entity);
        AuthorEntity actualEntity = authorDao.loadById(6L);
        assertEntityEquals(entity, actualEntity);
    }

    @Test
    public void delete() throws DaoException {
        Long id = 6L;
        authorDao.delete(id);

        AuthorEntity author = authorDao.loadById(id);
        assertNull(author);
    }

    @Test
    public void makeExpired() throws DaoException {
        Long id = 6L;
        authorDao.makeExpired(id);

        AuthorEntity author = authorDao.loadById(id);
        assertNotNull(author.getExpiredDate());
    }

    private void assertEntityEquals(AuthorEntity expected, AuthorEntity actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
    }
}
