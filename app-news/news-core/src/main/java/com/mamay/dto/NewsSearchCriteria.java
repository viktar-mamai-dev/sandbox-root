package com.mamay.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsSearchCriteria {

  private final List<Long> tagIdList;
  private final Long authorId;
}
