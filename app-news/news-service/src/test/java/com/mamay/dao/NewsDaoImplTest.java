package com.mamay.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.mamay.TestHolder;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class NewsDaoImplTest {

  @Autowired private NewsDao newsDao;

  @Test
  public void loadAll() throws NewsException {
    List<NewsEntity> newsList = newsDao.loadAll();
    assertEquals(20, newsList.size());
  }

  @Test
  public void loadById() throws NewsException {
    NewsEntity expectedEntity = newsDao.loadById(2L);

    assertNotNull(expectedEntity);
    assertEquals(Long.valueOf(2), expectedEntity.getId());
  }

  @Test
  public void loadByFilter() throws NewsException {
    List<Long> tagList = Arrays.asList((long) 3, (long) 4);
    Long authorId = 2L;
    NewsPageItem<NewsEntity> newsItem =
        newsDao.loadByFilter(new NewsSearchCriteria(tagList, authorId), 1, 6);
    assertEquals(1, newsItem.getNewsList().size());
  }

  @Test
  public void loadNextId() throws NewsException {
    List<Long> tagIdList = Arrays.asList(1L, 3L, 5L, 6L);
    Long authorId = 2L;
    Long newsId = 2L;
    Long nextId = newsDao.loadNextId(new NewsSearchCriteria(tagIdList, authorId), newsId);
    assertEquals(Long.valueOf(6), nextId);

    tagIdList = Arrays.asList(1L, 3L, 5L, 6L);
    authorId = null;
    newsId = 1L;
    nextId = newsDao.loadNextId(new NewsSearchCriteria(tagIdList, authorId), newsId);
    assertEquals(Long.valueOf(5), nextId);

    tagIdList = null;
    authorId = 6L;
    newsId = 6L;
    nextId = newsDao.loadNextId(new NewsSearchCriteria(tagIdList, authorId), newsId);
    assertNull(nextId);

    tagIdList = null;
    authorId = null;
    newsId = 11L;
    nextId = newsDao.loadNextId(new NewsSearchCriteria(tagIdList, authorId), newsId);
    assertEquals(Long.valueOf(16), nextId);
  }

  @Test
  public void loadPreviousId() throws NewsException {
    List<Long> tagIdList = Arrays.asList(1L, 3L, 5L, 6L);
    Long authorId = 2L;
    Long newsId = 2L;
    Long previousId = newsDao.loadPreviousId(new NewsSearchCriteria(tagIdList, authorId), newsId);
    assertEquals(Long.valueOf(5), previousId);

    tagIdList = Arrays.asList(1L, 3L, 5L, 6L);
    authorId = null;
    newsId = 1L;
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
  public void create() throws NewsException {
    NewsEntity expectedEntity = TestHolder.generateNewsEntity();
    Long id = newsDao.create(expectedEntity);
    assertNotNull(id);
    NewsEntity actualEntity = newsDao.loadById(id);
    assertEntityEquals(expectedEntity, actualEntity);
  }

  @Test
  public void attachAuthor() throws NewsException {
    newsDao.attachAuthor(Long.valueOf(7), Long.valueOf(4));
    NewsPageItem<NewsEntity> newsItem =
        newsDao.loadByFilter(new NewsSearchCriteria(null, Long.valueOf(4)), 1, 6);
    assertEquals(3, newsItem.getNewsList().size());
  }

  @Test
  public void attachTags() throws NewsException {
    List<Long> tagidList = Arrays.asList((long) 3, (long) 8);
    newsDao.attachTags(Long.valueOf(5), tagidList);
    NewsPageItem<NewsEntity> newsItem =
        newsDao.loadByFilter(new NewsSearchCriteria(Arrays.asList((long) 3), null), 1, 6);

    assertEquals(4, newsItem.getNewsList().size());
  }

  @Test
  public void detachTags() throws NewsException {
    Long newsId = 12L;
    newsDao.detachTags(newsId);

    List<Long> tagIdList = Arrays.asList(1L, 3L, 5L, 6L);
    List<NewsEntity> list =
        newsDao.loadByFilter(new NewsSearchCriteria(tagIdList, null), 1, 10).getNewsList();
    assertEquals(list.size(), 2);
  }

  @Test
  public void update() throws NewsException {
    NewsEntity expectedEntity = TestHolder.generateNewsEntity();
    expectedEntity.setId(2L);

    newsDao.update(expectedEntity);

    NewsEntity actualEntity = newsDao.loadById(2L);

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

  @Test
  public void updateAuthor() throws NewsException {
    Long newsId = 17L;
    Long newAuthorId = 16L;
    Long oldAuthorId = 13L;
    newsDao.updateAuthor(newsId, newAuthorId);
    NewsPageItem<NewsEntity> item =
        newsDao.loadByFilter(new NewsSearchCriteria(null, oldAuthorId), 1, 6);
    assertEquals(1, item.getNewsList().size());
  }

  private void assertEntityEquals(NewsEntity expectedEntity, NewsEntity actualEntity) {
    Assert.assertEquals(expectedEntity.getTitle(), actualEntity.getTitle());
    Assert.assertEquals(expectedEntity.getShortText(), actualEntity.getShortText());
    Assert.assertEquals(expectedEntity.getViewCount(), actualEntity.getViewCount());
  }
}
