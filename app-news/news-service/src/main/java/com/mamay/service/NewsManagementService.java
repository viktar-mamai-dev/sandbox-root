package com.mamay.service;

import java.util.LinkedList;
import java.util.List;

import com.mamay.entity.AuthorEntity;
import com.mamay.entity.CommentEntity;
import com.mamay.entity.NewsEntity;
import com.mamay.entity.TagEntity;
import com.mamay.exception.ServiceException;
import com.mamay.dto.NewsPageItem;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return constructVOlist(newsList);
	}

	@Transactional
	public NewsDto loadById(Long newsId) throws ServiceException {
		NewsEntity newsEntity = newsService.loadById(newsId);
		if (newsEntity == null) {
			throw new ServiceException("News with such id does not exist");
		}
		return constructValueObject(newsEntity);
	}

	@Transactional
	public NewsPageItem<NewsDto> loadByFilter(NewsSearchCriteria filteredItem, Integer pageNumber,
                                              int newsPerPage) throws ServiceException {
		NewsPageItem<NewsEntity> item = newsService.loadByFilter(filteredItem, pageNumber, newsPerPage);
		return new NewsPageItem<NewsDto>(constructVOlist(item.getNewsList()),
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

	private List<NewsDto> constructVOlist(List<NewsEntity> newsList) throws ServiceException {
		List<NewsDto> newsWrapperList = new LinkedList<NewsDto>();
		for (NewsEntity entity : newsList) {
			NewsDto newsObject = constructValueObject(entity);
			newsWrapperList.add(newsObject);
		}
		return newsWrapperList;
	}

	private NewsDto constructValueObject(NewsEntity entity) throws ServiceException {
		Long newsId = entity.getId();
		List<TagEntity> tagList = tagService.loadByNewsId(newsId);
		AuthorEntity author = authorService.loadByNewsId(newsId);
		List<CommentEntity> commentList = commentService.loadByNewsId(newsId);
		return new NewsDto(entity, tagList, author, commentList);
	}
}
