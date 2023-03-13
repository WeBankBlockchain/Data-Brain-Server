package com.webank.databrain.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paging {
    int pageNo;
    int pageSize;
}
