package com.webank.databrain.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagingResult<T> {
    //data list in this page
    private List<T> data;

    //current page
    private long page;

    //number of items in this page
    private long pageSize;

    //the total number of items available across all pages
    private long totalItems;

    //the total number of pages available
    private long totalPages;
}
