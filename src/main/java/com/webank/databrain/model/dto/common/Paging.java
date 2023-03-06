package com.webank.databrain.model.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paging {
    int pageNo;
    int pageSize;
}
