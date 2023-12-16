package com.mamay.service;

import com.mamay.dao.AuthorDao;
import com.mamay.dao.CommentDao;
import com.mamay.dao.NewsDao;
import com.mamay.dao.TagDao;
import com.mamay.dto.NewsDto;
import com.mamay.entity.AuthorEntity;
import com.mamay.entity.CommentEntity;
import com.mamay.entity.NewsEntity;
import com.mamay.entity.TagEntity;
import com.mamay.exception.NewsException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("newsManageService")
public class NewsManagementService {

  @Autowired private NewsService newsService;
  @Autowired private NewsDao newsDao;
  @Autowired private TagDao tagDao;
  @Autowired private AuthorDao authorDao;
  @Autowired private CommentDao commentDao;

  @Transactional
  public List<NewsDto> loadAll() {
    List<NewsEntity> newsList = newsDao.loadAll();
    return toDtoList(newsList);
  }

  @Transactional
  public NewsDto loadById(Long newsId) {
    NewsEntity newsEntity = newsDao.loadById(newsId);
    if (newsEntity == null) {
      throw new NewsException("News with such id does not exist");
    }
    return toDto(newsEntity);
  }

  @Transactional
  public Long create(NewsEntity newsEntity, List<Long> tagIdList, Long authorId) {
    Long newsId = newsDao.create(newsEntity);
    newsDao.attachAuthor(newsId, authorId);
    newsService.attachTags(newsId, tagIdList);
    return newsId;
  }

  @Transactional
  public Long update(NewsEntity newsEntity, List<Long> tagIdList, Long authorId) {
    newsDao.update(newsEntity);
    Long newsId = newsEntity.getId();
    newsDao.updateAuthor(newsId, authorId);
    newsDao.detachTags(newsId);
    if (tagIdList != null) {
      newsService.attachTags(newsId, tagIdList);
    }
    return newsId;
  }

  private List<NewsDto> toDtoList(List<NewsEntity> newsList) {
    List<NewsDto> list = new ArrayList<>();
    for (NewsEntity entity : newsList) {
      list.add(toDto(entity));
    }
    return list;
  }

  private NewsDto toDto(NewsEntity entity) {
    Long newsId = entity.getId();
    List<TagEntity> tagList = tagDao.loadByNewsId(newsId);
    AuthorEntity author = authorDao.loadByNewsId(newsId);
    List<CommentEntity> commentList = commentDao.loadByNewsId(newsId);
    return new NewsDto(entity, tagList, author, commentList);
  }
}
