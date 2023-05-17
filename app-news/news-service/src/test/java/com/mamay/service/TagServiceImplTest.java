package com.mamay.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.mamay.TestHolder;
import com.mamay.dao.TagDao;
import com.mamay.entity.TagEntity;
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
public class TagServiceImplTest {
  @InjectMocks private TagService tagService;

  @Mock private TagDao tagDao;

  @Test
  public void loadAll() {
    Mockito.doReturn(TestHolder.generateTagList()).when(tagDao).loadAll();
    List<TagEntity> actualTagList = tagService.loadAll();
    Mockito.verify(tagDao, Mockito.times(1)).loadAll();
    assertEquals(3, actualTagList.size());
  }

  @Test(expected = NewsException.class)
  public void loadAllFail() {
    Mockito.doThrow(NewsException.class).when(tagDao).loadAll();
    tagService.loadAll();
  }

  @Test
  public void loadById() {
    TagEntity entity = TestHolder.generateTag();
    Mockito.doReturn(entity).when(tagDao).loadById(entity.getId());
    TagEntity actualEntity = tagService.loadById(7L);
    Mockito.verify(tagDao, Mockito.times(1)).loadById(Matchers.anyLong());
    assertEntityEquals(actualEntity, entity);
  }

  @Test(expected = NewsException.class)
  public void loadByIdFail() {
    Mockito.doThrow(NewsException.class).when(tagDao).loadById(Matchers.anyLong());
    tagService.loadById(4L);
  }

  @Test
  public void create() {
    TagEntity entity = new TagEntity();
    entity.setName("color");
    Long id = tagService.create(entity);
    assertNotNull(id);
  }

  @Test
  public void update() {
    Mockito.verifyZeroInteractions(tagDao);
    TagEntity entity = TestHolder.generateTag();
    tagService.update(entity);
    Mockito.verify(tagDao, Mockito.atLeastOnce()).update(Matchers.any(TagEntity.class));
  }

  @Test(expected = NewsException.class)
  public void updateFail() {
    TagEntity entity = TestHolder.generateTag();
    Mockito.doThrow(NewsException.class).when(tagDao).update(entity);
    tagService.update(entity);
  }

  @Test
  public void delete() {
    tagService.delete(7L);
    Mockito.verify(tagDao).delete(Matchers.anyLong());
  }

  @Test(expected = NewsException.class)
  public void deleteFail() {
    Mockito.doThrow(NewsException.class).when(tagDao).delete(Matchers.anyLong());
    tagService.delete(4L);
  }

  private void assertEntityEquals(TagEntity entity, TagEntity actualEntity) {
    assertEquals(entity.getName(), actualEntity.getName());
  }
}
