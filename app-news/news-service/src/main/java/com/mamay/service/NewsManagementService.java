package com.mamay.service;

import com.mamay.dto.NewsDto;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
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

@Service
public class NewsManagementService {

  @Autowired private NewsService newsService;
  @Autowired private TagService tagService;
  @Autowired private AuthorService authorService;
  @Autowired private CommentService commentService;

  @Transactional
  public List<NewsDto> loadAll() {
    List<NewsEntity> newsList = newsService.loadAll();
    return toDtoList(newsList);
  }

  @Transactional
  public NewsDto loadById(Long newsId) {
    NewsEntity newsEntity = newsService.loadById(newsId);
    if (newsEntity == null) {
      throw new NewsException("News with such id does not exist");
    }
    return toDto(newsEntity);
  }

  @Transactional
  public NewsPageItem<NewsDto> loadByFilter(
      NewsSearchCriteria filteredItem, Integer pageNumber, int newsPerPage) {
    NewsPageItem<NewsEntity> item = newsService.loadByFilter(filteredItem, pageNumber, newsPerPage);
    return new NewsPageItem<NewsDto>(
        toDtoList(item.getNewsList()), item.getPageNumber(), item.getPageCount());
  }

  @Transactional
  public Long create(NewsEntity newsEntity, List<Long> tagIdList, Long authorId) {
    Long newsId = newsService.create(newsEntity);
    newsService.attachAuthor(newsId, authorId);
    newsService.attachTags(newsId, tagIdList);
    return newsId;
  }

  @Transactional
  public Long update(NewsEntity newsEntity, List<Long> tagIdList, Long authorId) {
    newsService.update(newsEntity);
    Long newsId = newsEntity.getId();
    newsService.updateAuthor(newsId, authorId);
    newsService.detachTags(newsId);
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
    List<TagEntity> tagList = tagService.loadByNewsId(newsId);
    AuthorEntity author = authorService.loadByNewsId(newsId);
    List<CommentEntity> commentList = commentService.loadByNewsId(newsId);
    return new NewsDto(entity, tagList, author, commentList);
  }
}
