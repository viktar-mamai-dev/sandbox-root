package com.mamay.service;

import com.mamay.dao.NewsDao;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NewsServiceImplTest {

  @InjectMocks private static NewsService newsService;

  @Mock private NewsDao newsDao;

  // TODO attachTags
}
