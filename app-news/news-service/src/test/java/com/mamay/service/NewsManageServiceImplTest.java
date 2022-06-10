package com.mamay.service;

import com.mamay.TestHolder;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.ServiceException;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NewsManageServiceImplTest {

    @InjectMocks
    private static NewsManagementService newsManageService;

    @Mock
    private TagService tagService;
    @Mock
    private AuthorService authorService;
    @Mock
    private NewsService newsService;

    @Test
    public void createWithTagsAndAuthor() throws ServiceException {
        NewsEntity actualEntity = TestHolder.generateNewsEntity();
        Mockito.doReturn(actualEntity.getId()).when(newsService).create(actualEntity);
        List<Long> unmodifiableList = Arrays.asList(ArrayUtils.toObject(new long[]{7, 8, 9, 10, 11}));
        List<Long> tagIdList = new ArrayList<Long>(unmodifiableList);
        tagIdList.add(null);
        Long authorId = Long.valueOf(12);
        Long id = newsManageService.create(actualEntity, tagIdList, authorId);

        Mockito.verify(authorService).loadById(authorId);
        Mockito.verify(newsService).create(actualEntity);
    }

    @Test
    public void updateWithTagsAndAuthor() throws ServiceException {
        NewsEntity actualEntity = TestHolder.generateNewsEntity();
        actualEntity.setVersion(1);
        List<Long> unmodifiableList = Arrays.asList(ArrayUtils.toObject(new long[]{7, 8, 9, 10, 11}));
        List<Long> tagIdList = new ArrayList<Long>(unmodifiableList);
        tagIdList.add(null);
        Long authorId = Long.valueOf(12);

        newsManageService.update(actualEntity, tagIdList, authorId);

        Mockito.verify(authorService).loadById(authorId);
        Mockito.verify(newsService).update(actualEntity);
    }
}
