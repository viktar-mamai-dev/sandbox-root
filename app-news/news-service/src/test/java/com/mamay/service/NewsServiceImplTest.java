package com.mamay.service;

import com.mamay.TestHolder;
import com.mamay.dao.NewsDao;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

    @InjectMocks
    private static NewsService newsService;

    @Mock
    private NewsDao newsDao;

    @Test
    public void loadAll() {
        Mockito.doReturn(TestHolder.generateNewsList()).when(newsDao).loadAll();

        assertNotNull(newsService.loadAll());
        assertEquals(3, newsService.loadAll().size());
    }

    @Test(expected = NewsException.class)
    public void loadAllFail() {
        Mockito.doThrow(NewsException.class).when(newsDao).loadAll();
        newsService.loadAll();
    }

    @Test
    public void loadById() {
        NewsEntity news1 = TestHolder.generateNewsEntity();
        Mockito.doReturn(news1).when(newsDao).loadById(1L);
        NewsEntity news2 = TestHolder.generateNewsEntity();
        Mockito.doReturn(news2).when(newsDao).loadById(3L);

        assertEquals(news1, newsService.loadById(1L));
        assertEquals(news2, newsService.loadById(3L));
        assertNull(newsService.loadById(2L));
    }

    @Test(expected = NewsException.class)
    public void loadByIdFail() {
        Mockito.doThrow(NewsException.class).when(newsDao).loadById(Matchers.anyLong());
        newsService.loadById(3L);
    }

    @Test
    public void loadByFilter() {
        int newsPerPage = 7;
        int pageNumber = 1;
        Long authorId = 3L;
        List<Long> tagIdList = Arrays.asList(1L, 3L, 5L, 6L);
        NewsSearchCriteria searchCriteria = new NewsSearchCriteria(tagIdList, authorId);
        List<NewsEntity> newsList = TestHolder.generateNewsList();
        Mockito.doReturn(newsList).when(newsDao).loadByFilter(searchCriteria, 1, newsPerPage);

        NewsPageItem<NewsEntity> item = newsService.loadByFilter(searchCriteria, null, newsPerPage);
        assertEquals(Integer.valueOf(5), item.getPageCount());
        assertEquals(Integer.valueOf(pageNumber), item.getPageNumber());
        assertEquals(newsList.size(), item.getNewsList().size());
    }

    @Test
    public void loadNextId() {
        Long authorId = 3L;
        List<Long> tagIdList = Arrays.asList(1L, 3L, 5L, 6L);
        Long newsId = 5L;
        NewsSearchCriteria filteredItem = new NewsSearchCriteria(tagIdList, authorId);
        newsService.loadNextId(filteredItem, newsId);
        Mockito.verify(newsDao).loadNextId(filteredItem, newsId);
    }

    @Test(expected = NewsException.class)
    public void loadNextIdFail() {
        Long authorId = 3L;
        List<Long> tagIdList = Arrays.asList(1L, 3L, 5L, 6L);
        Long newsId = 5L;
        NewsSearchCriteria filteredItem = new NewsSearchCriteria(tagIdList, authorId);
        Mockito.doThrow(NewsException.class).when(newsDao).loadNextId(filteredItem,
                newsId);
        newsService.loadNextId(filteredItem, newsId);
    }

    @Test
    public void loadPreviousId() {
        Long authorId = 3L;
        List<Long> tagIdList = Arrays.asList(1L, 3L, 5L, 6L);
        Long newsId = 5L;
        NewsSearchCriteria filteredItem = new NewsSearchCriteria(tagIdList, authorId);
        newsService.loadPreviousId(filteredItem, newsId);
        Mockito.verify(newsDao).loadPreviousId(filteredItem, newsId);
    }

    @Test(expected = NewsException.class)
    public void loadPreviousIdFail() {
        Long authorId = 3L;
        List<Long> tagIdList = Arrays.asList(1L, 3L, 5L, 6L);
        Long newsId = 5L;
        NewsSearchCriteria filteredItem = new NewsSearchCriteria(tagIdList, authorId);
        Mockito.doThrow(NewsException.class).when(newsDao).loadPreviousId(filteredItem,
                newsId);
        newsService.loadPreviousId(filteredItem, newsId);
    }

    @Test
    public void create() {
        NewsEntity entity = TestHolder.generateNewsEntity();
        Long id = newsService.create(entity);
        assertNotNull(id);
        Mockito.verify(newsDao).create(entity);
    }


    @Test(expected = NewsException.class)
    public void createFail() {
        NewsEntity entity = TestHolder.generateNewsEntity();
        Mockito.doThrow(NewsException.class).when(newsDao).create(Matchers.any(NewsEntity.class));
        newsService.create(entity);
    }

    @Test
    public void update() {
        NewsEntity news = TestHolder.generateNewsEntity();
        news.setTitle("italy beats scotland surprisingly");
        newsService.update(news);
        Mockito.verify(newsDao).update(news);
    }


    @Test
    public void delete() {
        newsService.delete(5L);
        newsService.delete(1L);
        newsService.delete(102L);

        Mockito.verify(newsDao, Mockito.times(3)).delete(Matchers.anyLong());
    }

    @Test
    public void deleteList() {
        List<Long> newsIdList = Arrays.asList((long) 3, (long) 4);
        newsService.deleteList(newsIdList);
        Mockito.verify(newsDao).deleteList(newsIdList);
    }

    @Test(expected = NewsException.class)
    public void deleteListFailNull() throws NewsException {
        newsService.deleteList(null);
    }

    @Test(expected = NewsException.class)
    public void deleteListFail() {
        Mockito.doThrow(NewsException.class).when(newsDao).deleteList(Mockito.anyListOf(Long.class));
        List<Long> idList = Arrays.asList(1L, 3L, 5L, 6L);
        newsService.deleteList(idList);
    }
}
