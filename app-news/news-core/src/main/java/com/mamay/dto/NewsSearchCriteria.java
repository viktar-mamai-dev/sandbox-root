package com.mamay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NewsSearchCriteria {

    private final List<Long> tagIdList;
    private final Long authorId;

}
