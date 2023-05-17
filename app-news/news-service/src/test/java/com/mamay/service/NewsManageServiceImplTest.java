package com.mamay.service;

import com.mamay.TestHolder;
import com.mamay.entity.NewsEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NewsManageServiceImplTest {

  @InjectMocks private static NewsManagementService newsManageService;

  @Mock private TagService tagService;
  @Mock private AuthorService authorService;
  @Mock private NewsService newsService;

  @Test
  public void createWithTagsAndAuthor() {
    NewsEntity actualEntity = TestHolder.generateNewsEntity();
    Mockito.doReturn(actualEntity.getId()).when(newsService).create(actualEntity);
    List<Long> unmodifiableList = Arrays.asList(7L, 8L, 9L, 10L, 11L);
    List<Long> tagIdList = new ArrayList<Long>(unmodifiableList);
    tagIdList.add(null);
    Long authorId = 12L;
    Long id = newsManageService.create(actualEntity, tagIdList, authorId);

    Mockito.verify(authorService).loadById(authorId);
    Mockito.verify(newsService).create(actualEntity);
  }

  @Test
  public void updateWithTagsAndAuthor() {
    NewsEntity actualEntity = TestHolder.generateNewsEntity();
    actualEntity.setVersion(1);
    List<Long> unmodifiableList = Arrays.asList(7L, 8L, 9L, 10L, 11L);
    List<Long> tagIdList = new ArrayList<Long>(unmodifiableList);
    tagIdList.add(null);
    Long authorId = 12L;

    newsManageService.update(actualEntity, tagIdList, authorId);

    Mockito.verify(authorService).loadById(authorId);
    Mockito.verify(newsService).update(actualEntity);
  }
}
