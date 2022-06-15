package com.mamay.dto;

import lombok.Getter;

import java.util.List;

/**
 * <p>
 * value object that stores data about specific list of objects that corresponds to one page on user view
 */
@Getter
public class NewsPageItem<T> {

    private final List<T> newsList;
    private final Integer pageNumber;
    private final Integer pageCount;

    public NewsPageItem(List<T> newsList, Integer pageNumber, Integer pageCount) {
        this.newsList = newsList;
        this.pageNumber = pageNumber;
        this.pageCount = pageCount;
    }
}
