package com.mamay.service;

import com.mamay.dao.NewsDao;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("newsService")
public class NewsService {
  @Autowired private NewsDao newsDao;

  public void attachTags(Long newsId, List<Long> tagId) {
    if (tagId == null || tagId.isEmpty()) {
      return;
    }
    tagId.removeAll(Collections.singleton(null));
    newsDao.attachTags(newsId, tagId);
  }
}
