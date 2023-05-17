package com.mamay.service;

import com.mamay.TestHolder;
import com.mamay.dao.AuthorDao;
import com.mamay.entity.AuthorEntity;
import com.mamay.exception.NewsException;
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
    public void loadAll() {
        Mockito.doReturn(TestHolder.generateAuthorList()).when(authorDao).loadAll();
        List<AuthorEntity> actualTagList = authorService.loadAll();
        Mockito.verify(authorDao, Mockito.times(1)).loadAll();
        assertEquals(3, actualTagList.size());
    }

    @Test(expected = NewsException.class)
    public void loadAllFail() {
        Mockito.doThrow(NewsException.class).when(authorDao).loadAll();
        authorService.loadAll();
    }

    @Test
    public void loadActiveAuthors() {
        Mockito.doReturn(TestHolder.generateAuthorList()).when(authorDao).loadActiveAuthors();
        List<AuthorEntity> actualTagList = authorService.loadActiveAuthors();
        Mockito.verify(authorDao, Mockito.times(1)).loadActiveAuthors();
        assertEquals(3, actualTagList.size());
    }

    @Test
    public void loadById() {
        AuthorEntity entity = new AuthorEntity();
        String authorName = "molenkov";
        entity.setName(authorName);
        Mockito.doReturn(entity).when(authorDao).loadById(4L);
        AuthorEntity actualEntity = authorService.loadById(4L);
        Mockito.verify(authorDao, Mockito.times(1)).loadById(Matchers.anyLong());
        assertEquals(authorName, actualEntity.getName());
    }

    @Test(expected = NewsException.class)
    public void loadByIdFail() {
        Mockito.doThrow(NewsException.class).when(authorDao).loadById(Matchers.anyLong());
        authorService.loadById(4L);
    }

    @Test
    public void create() {
        AuthorEntity entity = new AuthorEntity();
        entity.setName("petrenko");
        Long id = authorService.create(entity);
        assertNotNull(id);
    }

    @Test(expected = NewsException.class)
    public void createFail() {
        Mockito.doThrow(NewsException.class).when(authorDao).create(Matchers.any(AuthorEntity.class));
        AuthorEntity entity = new AuthorEntity();
        entity.setName("petrenko");
        authorService.create(entity);
    }

    @Test
    public void update() {
        AuthorEntity entity = TestHolder.generateAuthor();
        entity.setId(3L);
        authorService.update(entity);
        Mockito.verify(authorDao, Mockito.atLeastOnce()).update(Matchers.any(AuthorEntity.class));
    }

    @Test
    public void delete() {
        authorService.delete(2L);
        authorService.delete(4L);
        Mockito.verify(authorDao, Mockito.times(2)).delete(Matchers.anyLong());
    }

    @Test(expected = NewsException.class)
    public void deleteFail() {
        Mockito.doThrow(NewsException.class).when(authorDao).delete(Matchers.anyLong());
        authorService.delete(4L);
    }

    @Test
    public void makeExpired() {
        Long authorId = 2L;
        authorService.makeExpired(authorId);
        Mockito.verify(authorDao).makeExpired(authorId);
    }
}
