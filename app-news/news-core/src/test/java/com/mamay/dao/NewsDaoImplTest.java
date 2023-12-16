package com.mamay.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mamay.TestHolder;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
@TestExecutionListeners(listeners = {DbUnitTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@DatabaseSetup(value = {"/dbunit/news-data.xml", "/dbunit/news_tag-data.xml", "/dbunit/news_author-data.xml"})
public class NewsDaoImplTest {

    @Autowired
    private NewsDao newsDao;

    @Test
    public void loadAll() throws NewsException {
        List<NewsEntity> newsList = newsDao.loadAll();
        assertEquals(20, newsList.size());
    }

    @Test
    public void loadById() throws NewsException {
        NewsEntity expectedEntity = newsDao.loadById(Long.valueOf(2));

        assertNotNull(expectedEntity);
        assertEquals(Long.valueOf(2), expectedEntity.getId());
    }

    @Test
    public void create() throws NewsException {
        NewsEntity expectedEntity = TestHolder.generateNewsEntity();
        Long id = newsDao.create(expectedEntity);
        assertNotNull(id);
        NewsEntity actualEntity = newsDao.loadById(id);
        assertEntityEquals(expectedEntity, actualEntity);
    }

    @Test
    public void update() throws NewsException {
        NewsEntity expectedEntity = TestHolder.generateNewsEntity();
        expectedEntity.setId(2L);

        newsDao.update(expectedEntity);

        NewsEntity actualEntity = newsDao.loadById(Long.valueOf(2));

        assertEntityEquals(expectedEntity, actualEntity);
    }

    @Test
    public void delete() throws NewsException {
        newsDao.delete(Long.valueOf(2));

        NewsEntity entity = newsDao.loadById(Long.valueOf(2));

        assertNull(entity);
    }

    @Test
    public void deleteList() throws NewsException {
        List<Long> newsIdList = Arrays.asList((long) 3, (long) 2);
        newsDao.deleteList(newsIdList);
        NewsEntity entity = newsDao.loadById(Long.valueOf(2));
        assertNull(entity);

        assertEquals(18, newsDao.loadAll().size());
    }

    private void assertEntityEquals(NewsEntity expectedEntity, NewsEntity actualEntity) {

        Assert.assertEquals(expectedEntity.getTitle(), actualEntity.getTitle());
    }

}
