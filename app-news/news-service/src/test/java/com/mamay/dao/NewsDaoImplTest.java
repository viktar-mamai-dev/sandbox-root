package com.mamay.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.mamay.TestHolder;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.NewsException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class NewsDaoImplTest {

  @Autowired private NewsDao newsDao;

  @Test
  public void loadAll() throws NewsException {
    List<NewsEntity> newsList = newsDao.loadAll();
    Assertions.assertEquals(20, newsList.size());
  }

  @Test
  public void loadById() throws NewsException {
    NewsEntity expectedEntity = newsDao.loadById(2L);

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

  private void assertEntityEquals(NewsEntity expectedEntity, NewsEntity actualEntity) {
    assertEquals(expectedEntity.getTitle(), actualEntity.getTitle());
  }
}
