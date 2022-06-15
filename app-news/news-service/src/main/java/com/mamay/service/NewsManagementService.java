package com.mamay.service;

import com.mamay.dto.NewsDto;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.AuthorEntity;
import com.mamay.entity.CommentEntity;
import com.mamay.entity.NewsEntity;
import com.mamay.entity.TagEntity;
import com.mamay.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("newsManageService")
public class NewsManagementService {

    @Autowired
    private NewsService newsService;
    @Autowired
    private TagService tagService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CommentService commentService;

    @Transactional
    public List<NewsDto> loadAll() throws ServiceException {
        List<NewsEntity> newsList = newsService.loadAll();
        return toDtoList(newsList);
    }

    @Transactional
    public NewsDto loadById(Long newsId) throws ServiceException {
        NewsEntity newsEntity = newsService.loadById(newsId);
        if (newsEntity == null) {
            throw new ServiceException("News with such id does not exist");
        }
        return toDto(newsEntity);
    }

    @Transactional
    public NewsPageItem<NewsDto> loadByFilter(NewsSearchCriteria filteredItem, Integer pageNumber,
                                              int newsPerPage) throws ServiceException {
        NewsPageItem<NewsEntity> item = newsService.loadByFilter(filteredItem, pageNumber, newsPerPage);
        return new NewsPageItem<NewsDto>(toDtoList(item.getNewsList()),
                item.getPageNumber(), item.getPageCount());
    }

    @Transactional
    public Long create(NewsEntity newsEntity, List<Long> tagIdList, Long authorId) throws ServiceException {
        Long newsId = newsService.create(newsEntity);
        newsService.attachAuthor(newsId, authorId);
        newsService.attachTags(newsId, tagIdList);
        return newsId;
    }

    @Transactional
    public Long update(NewsEntity newsEntity, List<Long> tagIdList, Long authorId) throws ServiceException {
        newsService.update(newsEntity);
        Long newsId = newsEntity.getId();
        newsService.updateAuthor(newsId, authorId);
        newsService.detachTags(newsId);
        if (tagIdList != null) {
            newsService.attachTags(newsId, tagIdList);
        }
        return newsId;
    }

    private List<NewsDto> toDtoList(List<NewsEntity> newsList) throws ServiceException {
        List<NewsDto> list = new ArrayList<>();
        for (NewsEntity entity : newsList) {
            list.add(toDto(entity));
        }
        return list;
    }

    private NewsDto toDto(NewsEntity entity) throws ServiceException {
        Long newsId = entity.getId();
        List<TagEntity> tagList = tagService.loadByNewsId(newsId);
        AuthorEntity author = authorService.loadByNewsId(newsId);
        List<CommentEntity> commentList = commentService.loadByNewsId(newsId);
        return new NewsDto(entity, tagList, author, commentList);
    }
}
