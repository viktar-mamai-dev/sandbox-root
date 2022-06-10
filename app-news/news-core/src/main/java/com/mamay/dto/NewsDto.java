package com.mamay.dto;

import com.mamay.entity.AuthorEntity;
import com.mamay.entity.CommentEntity;
import com.mamay.entity.NewsEntity;
import com.mamay.entity.TagEntity;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * entity that wrappes news entity and associated authors, tags, comments
 */
@Getter
public class NewsDto {

    private final NewsEntity newsEntity;
    private final List<TagEntity> tagList;
    private final AuthorEntity authorEntity;
    private final List<CommentEntity> commentList;

    public NewsDto(NewsEntity newsEntity, List<TagEntity> tagList, AuthorEntity authorEntity,
                   List<CommentEntity> commentList) {
        this.newsEntity = newsEntity;
        this.tagList = tagList;
        this.authorEntity = authorEntity;
        this.commentList = commentList;
    }

    public List<TagEntity> getTagList() {
        return Collections.unmodifiableList(tagList);
    }

    public List<CommentEntity> getCommentList() {
        return Collections.unmodifiableList(commentList);
    }

}
