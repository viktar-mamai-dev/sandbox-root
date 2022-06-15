package com.mamay.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mamay.TestHolder;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.DaoException;
import org.apache.commons.lang.ArrayUtils;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
@TestExecutionListeners(listeners = {DbUnitTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@DatabaseSetup(value = {"/dbunit/news-data.xml", "/dbunit/news_tag-data.xml", "/dbunit/news_author-data.xml"})
public class NewsDaoImplTest {

    @Autowired
    private NewsDao newsDao;

    @Test
    public void loadAll() throws DaoException {
        List<NewsEntity> newsList = newsDao.loadAll();
        assertEquals(20, newsList.size());
    }

    @Test
    public void loadById() throws DaoException {
        NewsEntity expectedEntity = newsDao.loadById(Long.valueOf(2));

        assertNotNull(expectedEntity);
        assertEquals(Long.valueOf(2), expectedEntity.getId());
    }

    @Test
    public void loadByFilter() throws DaoException {
        List<Long> tagList = Arrays.asList((long) 3, (long) 4);
        Long authorId = Long.valueOf(2);
        NewsPageItem<NewsEntity> newsItem = newsDao.loadByFilter(new NewsSearchCriteria(tagList, authorId), 1, 6);
        assertEquals(1, newsItem.getNewsList().size());
    }

    @Test
    public void loadNextId() throws DaoException {
        List<Long> tagIdList = Arrays.asList(ArrayUtils.toObject(new long[]{1, 3, 5, 6}));
        Long authorId = Long.valueOf(2);
        Long newsId = Long.valueOf(2);
        Long nextId = newsDao.loadNextId(new NewsSearchCriteria(tagIdList, authorId), newsId);
        assertEquals(Long.valueOf(6), nextId);

        tagIdList = Arrays.asList(ArrayUtils.toObject(new long[]{1, 3, 5, 6}));
        authorId = null;
        newsId = Long.valueOf(1);
        nextId = newsDao.loadNextId(new NewsSearchCriteria(tagIdList, authorId), newsId);
        assertEquals(Long.valueOf(5), nextId);

        tagIdList = null;
        authorId = Long.valueOf(6);
        newsId = Long.valueOf(6);
        nextId = newsDao.loadNextId(new NewsSearchCriteria(tagIdList, authorId), newsId);
        assertNull(nextId);

        tagIdList = null;
        authorId = null;
        newsId = Long.valueOf(11);
        nextId = newsDao.loadNextId(new NewsSearchCriteria(tagIdList, authorId), newsId);
        assertEquals(Long.valueOf(16), nextId);
    }

    @Test
    public void loadPreviousId() throws DaoException {
        List<Long> tagIdList = Arrays.asList(ArrayUtils.toObject(new long[]{1, 3, 5, 6}));
        Long authorId = Long.valueOf(2);
        Long newsId = Long.valueOf(2);
        Long previousId = newsDao.loadPreviousId(new NewsSearchCriteria(tagIdList, authorId), newsId);
        assertEquals(Long.valueOf(5), previousId);

        tagIdList = Arrays.asList(ArrayUtils.toObject(new long[]{1, 3, 5, 6}));
        authorId = null;
        newsId = Long.valueOf(1);
        previousId = newsDao.loadPreviousId(new NewsSearchCriteria(tagIdList, authorId), newsId);
        assertNull(previousId);

        tagIdList = null;
        authorId = Long.valueOf(2);
        newsId = Long.valueOf(2);
        previousId = newsDao.loadPreviousId(new NewsSearchCriteria(tagIdList, authorId), newsId);
        assertEquals(Long.valueOf(5), previousId);

        tagIdList = null;
        authorId = null;
        newsId = Long.valueOf(11);
        previousId = newsDao.loadPreviousId(new NewsSearchCriteria(tagIdList, authorId), newsId);
        assertEquals(Long.valueOf(20), previousId);
    }

    @Test
    public void create() throws DaoException {
        NewsEntity expectedEntity = TestHolder.generateNewsEntity();
        Long id = newsDao.create(expectedEntity);
        assertNotNull(id);
        NewsEntity actualEntity = newsDao.loadById(id);
        assertEntityEquals(expectedEntity, actualEntity);
    }

    @Test
    public void attachAuthor() throws DaoException {
        newsDao.attachAuthor(Long.valueOf(7), Long.valueOf(4));
        NewsPageItem<NewsEntity> newsItem = newsDao.loadByFilter(new NewsSearchCriteria(null, Long.valueOf(4)), 1, 6);
        assertEquals(3, newsItem.getNewsList().size());
    }

    @Test
    public void attachTags() throws DaoException {
        List<Long> tagidList = Arrays.asList((long) 3, (long) 8);
        newsDao.attachTags(Long.valueOf(5), tagidList);
        NewsPageItem<NewsEntity> newsItem = newsDao.loadByFilter(new NewsSearchCriteria(Arrays.asList((long) 3), null),
                1, 6);

        assertEquals(4, newsItem.getNewsList().size());
    }

    @Test
    public void detachTags() throws DaoException {
        Long newsId = Long.valueOf(12);
        newsDao.detachTags(newsId);

        List<Long> tagIdList = Arrays.asList(ArrayUtils.toObject(new long[]{8, 12}));
        List<NewsEntity> list = newsDao.loadByFilter(new NewsSearchCriteria(tagIdList, null), 1, 10).getNewsList();
        assertEquals(list.size(), 2);
    }

    @Test
    public void update() throws DaoException {
        NewsEntity expectedEntity = TestHolder.generateNewsEntity();
        expectedEntity.setId(2L);

        newsDao.update(expectedEntity);

        NewsEntity actualEntity = newsDao.loadById(Long.valueOf(2));

        assertEntityEquals(expectedEntity, actualEntity);
    }

    @Test
    public void delete() throws DaoException {
        newsDao.delete(Long.valueOf(2));

        NewsEntity entity = newsDao.loadById(Long.valueOf(2));

        assertNull(entity);
    }

    @Test
    public void deleteList() throws DaoException {
        List<Long> newsIdList = Arrays.asList((long) 3, (long) 2);
        newsDao.deleteList(newsIdList);
        NewsEntity entity = newsDao.loadById(Long.valueOf(2));
        assertNull(entity);

        assertEquals(18, newsDao.loadAll().size());
    }

    @Test
    public void updateAuthor() throws DaoException {
        Long newsId = Long.valueOf(17);
        Long newAuthorId = Long.valueOf(16);
        Long oldAuthorId = Long.valueOf(13);
        newsDao.updateAuthor(newsId, newAuthorId);
        NewsPageItem<NewsEntity> item = newsDao.loadByFilter(new NewsSearchCriteria(null, oldAuthorId), 1, 6);
        assertEquals(1, item.getNewsList().size());
    }

    private void assertEntityEquals(NewsEntity expectedEntity, NewsEntity actualEntity) {

        Assert.assertEquals(expectedEntity.getTitle(), actualEntity.getTitle());
        Assert.assertEquals(expectedEntity.getShortText(), actualEntity.getShortText());
        Assert.assertEquals(expectedEntity.getFullText(), actualEntity.getFullText());
    }

}
