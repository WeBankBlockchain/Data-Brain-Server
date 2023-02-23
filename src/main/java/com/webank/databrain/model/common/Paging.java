package com.webank.databrain.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paging {
    int pageNo;
    int pageSize;
}
