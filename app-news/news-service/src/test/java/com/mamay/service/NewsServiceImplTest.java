package com.mamay.service;

import com.mamay.dao.NewsDao;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

  @InjectMocks private static NewsService newsService;

  @Mock private NewsDao newsDao;

  // TODO attachTags
}
