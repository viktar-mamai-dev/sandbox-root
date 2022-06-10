package com.mamay.service;

import com.mamay.TestHolder;
import com.mamay.dao.NewsDao;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.DaoException;
import com.mamay.exception.ServiceException;
import org.apache.commons.lang.ArrayUtils;
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
    public void loadAll() throws ServiceException, DaoException {
        Mockito.doReturn(TestHolder.generateNewsList()).when(newsDao).loadAll();

        assertNotNull(newsService.loadAll());
        assertEquals(3, newsService.loadAll().size());
    }

    @Test(expected = ServiceException.class)
    public void loadAllFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(newsDao).loadAll();
        newsService.loadAll();
    }

    @Test
    public void loadById() throws ServiceException, DaoException {
        NewsEntity news1 = TestHolder.generateNewsEntity();
        Mockito.doReturn(news1).when(newsDao).loadById(Long.valueOf(1));
        NewsEntity news2 = TestHolder.generateNewsEntity();
        Mockito.doReturn(news2).when(newsDao).loadById(Long.valueOf(3));

        assertEquals(news1, newsService.loadById(Long.valueOf(1)));
        assertEquals(news2, newsService.loadById(Long.valueOf(3)));
        assertNull(newsService.loadById(Long.valueOf(2)));
    }

    @Test(expected = ServiceException.class)
    public void loadByIdFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(newsDao).loadById(Matchers.anyLong());
        newsService.loadById(Long.valueOf(3));
    }

    @Test
    public void loadByFilter() throws ServiceException, DaoException {
        int newsPerPage = 7;
        int pageNumber = 1;
        Long authorId = Long.valueOf(3);
        List<Long> tagIdList = Arrays.asList(ArrayUtils.toObject(new long[]{1, 3, 5, 6}));
        NewsSearchCriteria searchCriteria = new NewsSearchCriteria(tagIdList, authorId);
        List<NewsEntity> newsList = TestHolder.generateNewsList();
        Mockito.doReturn(newsList).when(newsDao).loadByFilter(searchCriteria, 1, newsPerPage);

        NewsPageItem item = newsService.loadByFilter(searchCriteria, null, newsPerPage);
        assertEquals(Integer.valueOf(5), item.getPageCount());
        assertEquals(Integer.valueOf(pageNumber), item.getPageNumber());
        assertEquals(newsList.size(), item.getNewsList().size());
    }

    @Test
    public void loadNextId() throws ServiceException, DaoException {
        Long authorId = Long.valueOf(3);
        List<Long> tagIdList = Arrays.asList(ArrayUtils.toObject(new long[]{1, 3, 5, 6}));
        Long newsId = Long.valueOf(5);
        NewsSearchCriteria filteredItem = new NewsSearchCriteria(tagIdList, authorId);
        newsService.loadNextId(filteredItem, newsId);
        Mockito.verify(newsDao).loadNextId(filteredItem, newsId);
    }

    @Test(expected = ServiceException.class)
    public void loadNextIdFail() throws ServiceException, DaoException {
        Long authorId = Long.valueOf(3);
        List<Long> tagIdList = Arrays.asList(ArrayUtils.toObject(new long[]{1, 3, 5, 6}));
        Long newsId = Long.valueOf(5);
        NewsSearchCriteria filteredItem = new NewsSearchCriteria(tagIdList, authorId);
        Mockito.doThrow(DaoException.class).when(newsDao).loadNextId(filteredItem,
                newsId);
        newsService.loadNextId(filteredItem, newsId);
    }

    @Test
    public void loadPreviousId() throws ServiceException, DaoException {
        Long authorId = Long.valueOf(3);
        List<Long> tagIdList = Arrays.asList(ArrayUtils.toObject(new long[]{
                1, 3, 5, 6}));
        Long newsId = Long.valueOf(5);
        NewsSearchCriteria filteredItem = new NewsSearchCriteria(tagIdList, authorId);
        newsService.loadPreviousId(filteredItem, newsId);
        Mockito.verify(newsDao).loadPreviousId(filteredItem, newsId);
    }

    @Test(expected = ServiceException.class)
    public void loadPreviousIdFail() throws ServiceException, DaoException {
        Long authorId = Long.valueOf(3);
        List<Long> tagIdList = Arrays.asList(ArrayUtils.toObject(new long[]{1, 3, 5, 6}));
        Long newsId = Long.valueOf(5);
        NewsSearchCriteria filteredItem = new NewsSearchCriteria(tagIdList, authorId);
        Mockito.doThrow(DaoException.class).when(newsDao).loadPreviousId(filteredItem,
                newsId);
        newsService.loadPreviousId(filteredItem, newsId);
    }

    @Test
    public void create() throws ServiceException, DaoException {
        NewsEntity entity = TestHolder.generateNewsEntity();
        Long id = newsService.create(entity);
        assertNotNull(id);
        Mockito.verify(newsDao).create(entity);
    }


    @Test(expected = ServiceException.class)
    public void createFail() throws ServiceException, DaoException {
        NewsEntity entity = TestHolder.generateNewsEntity();
        Mockito.doThrow(DaoException.class).when(newsDao).create(Matchers.any(NewsEntity.class));
        newsService.create(entity);
    }

    @Test
    public void update() throws ServiceException, DaoException {
        NewsEntity news = TestHolder.generateNewsEntity();
        news.setTitle("italy beats scotland surprisingly");
        newsService.update(news);
        Mockito.verify(newsDao).update(news);
    }


    @Test
    public void delete() throws ServiceException, DaoException {
        newsService.delete(Long.valueOf(5));
        newsService.delete(Long.valueOf(1));
        newsService.delete(Long.valueOf(102));

        Mockito.verify(newsDao, Mockito.times(3)).delete(Matchers.anyLong());
    }

    @Test
    public void deleteList() throws ServiceException, DaoException {
        List<Long> newsIdList = Arrays.asList((long) 3, (long) 4);
        newsService.deleteList(newsIdList);
        Mockito.verify(newsDao).deleteList(newsIdList);
    }

    @Test(expected = ServiceException.class)
    public void deleteListFailNull() throws ServiceException, DaoException {
        newsService.deleteList(null);
    }

    @Test(expected = ServiceException.class)
    public void deleteListFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(newsDao).deleteList(Mockito.anyListOf(Long.class));
        List<Long> idList = Arrays.asList(ArrayUtils.toObject(new long[]{1, 3, 5, 6}));
        newsService.deleteList(idList);
    }
}
