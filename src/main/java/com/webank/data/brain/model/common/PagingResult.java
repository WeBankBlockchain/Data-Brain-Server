package com.webank.data.brain.model.common;

import lombok.Data;

import java.util.List;

@Data
public class PagingResult<T> {
    //data list in this page
    private List<T> data;

    //current page
    private int page;

    //number of items in this page
    private int pageSize;

    //the total number of items available across all pages
    private int totalItems;

    //the total number of pages available
    private int totalPages;
}
