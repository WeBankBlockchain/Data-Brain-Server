package com.webank.databrain.model.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paging {
    int pageNo;
    int pageSize;
}
