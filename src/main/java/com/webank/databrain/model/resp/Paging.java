package com.webank.databrain.model.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paging {
    int pageNo;
    int pageSize;
}
