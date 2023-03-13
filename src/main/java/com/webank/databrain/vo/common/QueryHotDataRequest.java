package com.webank.databrain.vo.common;


import lombok.Data;

@Data
public class QueryHotDataRequest extends CommonRequest{
    private int topCount;
}
