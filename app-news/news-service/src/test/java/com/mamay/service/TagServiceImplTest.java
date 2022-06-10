package com.mamay.service;

import com.mamay.TestHolder;
import com.mamay.dao.TagDao;
import com.mamay.entity.TagEntity;
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

@RunWith(MockitoJUnitRunner.class)
public class TagServiceImplTest {
    @InjectMocks
    private TagService tagService;

    @Mock
    private TagDao tagDao;

    @Test
    public void loadAll() throws ServiceException, DaoException {
        Mockito.doReturn(TestHolder.generateTagList()).when(tagDao).loadAll();
        List<TagEntity> actualTagList = tagService.loadAll();
        Mockito.verify(tagDao, Mockito.times(1)).loadAll();
        assertEquals(3, actualTagList.size());
    }

    @Test(expected = ServiceException.class)
    public void loadAllFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(tagDao).loadAll();
        tagService.loadAll();
    }

    @Test
    public void loadById() throws ServiceException, DaoException {
        TagEntity entity = TestHolder.generateTag();
        Mockito.doReturn(entity).when(tagDao).loadById(entity.getId());
        TagEntity actualEntity = tagService.loadById(Long.valueOf(7));
        Mockito.verify(tagDao, Mockito.times(1)).loadById(Matchers.anyLong());
        assertEntityEquals(actualEntity, entity);
    }

    @Test(expected = ServiceException.class)
    public void loadByIdFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(tagDao).loadById(Matchers.anyLong());
        tagService.loadById(Long.valueOf(4));
    }

    @Test
    public void create() throws ServiceException, DaoException {
        TagEntity entity = new TagEntity();
        entity.setName("color");
        Long id = tagService.create(entity);
        assertNotNull(id);
    }

    @Test
    public void update() throws ServiceException, DaoException {
        Mockito.verifyZeroInteractions(tagDao);
        TagEntity entity = TestHolder.generateTag();
        tagService.update(entity);
        Mockito.verify(tagDao, Mockito.atLeastOnce()).update(Matchers.any(TagEntity.class));
    }

    @Test(expected = ServiceException.class)
    public void updateFail() throws ServiceException, DaoException {
        TagEntity entity = TestHolder.generateTag();
        Mockito.doThrow(DaoException.class).when(tagDao).update(entity);
        tagService.update(entity);
    }

    @Test
    public void delete() throws ServiceException, DaoException {
        tagService.delete(Long.valueOf(7));
        Mockito.verify(tagDao).delete(Matchers.anyLong());
    }

    @Test(expected = ServiceException.class)
    public void deleteFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(tagDao).delete(Matchers.anyLong());
        tagService.delete(Long.valueOf(4));
    }

    private void assertEntityEquals(TagEntity entity, TagEntity actualEntity) {
        assertEquals(entity.getName(), actualEntity.getName());
    }
}
