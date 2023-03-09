package com.webank.databrain.model.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResult<T> {

    private List<T> items;

    //current page
    private long page;

    //number of items in this page
    private long pageSize;

//    //the total number of items available across all pages
//    private long totalItems;
//
//    //the total number of pages available
//    private long totalPages;

}
