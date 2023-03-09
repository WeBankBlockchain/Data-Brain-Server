package com.webank.databrain.vo.common;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
public class PageListData<T> {
    private int totalCount = 0;
    private int pageCount = 0;
    private List<T> itemList = Collections.EMPTY_LIST;
}
