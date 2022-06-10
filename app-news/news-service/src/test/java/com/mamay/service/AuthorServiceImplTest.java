package com.mamay.service;

import com.mamay.TestHolder;
import com.mamay.dao.AuthorDao;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.DaoException;
import com.mamay.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplTest {
    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorDao authorDao;

    @Test
    public void loadAll() throws ServiceException, DaoException {
        Mockito.doReturn(TestHolder.generateAuthorList()).when(authorDao).loadAll();
        List<AuthorEntity> actualTagList = authorService.loadAll();
        Mockito.verify(authorDao, Mockito.times(1)).loadAll();
        assertEquals(3, actualTagList.size());
    }

    @Test(expected = ServiceException.class)
    public void loadAllFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(authorDao).loadAll();
        authorService.loadAll();
    }

    @Test
    public void loadActiveAuthors() throws ServiceException, DaoException {
        Mockito.doReturn(TestHolder.generateAuthorList()).when(authorDao).loadActiveAuthors();
        List<AuthorEntity> actualTagList = authorService.loadActiveAuthors();
        Mockito.verify(authorDao, Mockito.times(1)).loadActiveAuthors();
        assertEquals(3, actualTagList.size());
    }

    @Test
    public void loadById() throws ServiceException, DaoException {
        AuthorEntity entity = new AuthorEntity();
        String authorName = "molenkov";
        entity.setName(authorName);
        Mockito.doReturn(entity).when(authorDao).loadById(Long.valueOf(4));
        AuthorEntity actualEntity = authorService.loadById(Long.valueOf(4));
        Mockito.verify(authorDao, Mockito.times(1)).loadById(Matchers.anyLong());
        assertEquals(authorName, actualEntity.getName());
    }

    @Test(expected = ServiceException.class)
    public void loadByIdFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(authorDao).loadById(Matchers.anyLong());
        authorService.loadById(Long.valueOf(4));
    }

    @Test
    public void create() throws ServiceException, DaoException {
        AuthorEntity entity = new AuthorEntity();
        entity.setName("petrenko");
        Long id = authorService.create(entity);
        assertNotNull(id);
    }

    @Test(expected = ServiceException.class)
    public void createFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(authorDao).create(Matchers.any(AuthorEntity.class));
        AuthorEntity entity = new AuthorEntity();
        entity.setName("petrenko");
        authorService.create(entity);
    }

    @Test
    public void update() throws ServiceException, DaoException {
        AuthorEntity entity = TestHolder.generateAuthor();
        entity.setId(Long.valueOf(3));
        authorService.update(entity);
        Mockito.verify(authorDao, Mockito.atLeastOnce()).update(Matchers.any(AuthorEntity.class));
    }

    @Test
    public void delete() throws ServiceException, DaoException {
        authorService.delete(Long.valueOf(2));
        authorService.delete(Long.valueOf(4));
        Mockito.verify(authorDao, Mockito.times(2)).delete(Matchers.anyLong());
    }

    @Test(expected = ServiceException.class)
    public void deleteFail() throws ServiceException, DaoException {
        Mockito.doThrow(DaoException.class).when(authorDao).delete(Matchers.anyLong());
        authorService.delete(Long.valueOf(4));
    }

    @Test
    public void makeExpired() throws ServiceException, DaoException {
        Long authorId = Long.valueOf(2);
        authorService.makeExpired(authorId);
        Mockito.verify(authorDao).makeExpired(authorId);
    }
}
