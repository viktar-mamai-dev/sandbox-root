package com.mamay.dto;

import java.util.List;

public class NewsSearchCriteria {

    private final Long authorId;
    private final List<Long> tagIdList;

    public NewsSearchCriteria(List<Long> tagIdList, Long authorId) {
        this.authorId = authorId;
        this.tagIdList = tagIdList;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public List<Long> getTagIdList() {
        return tagIdList;
    }

}
