package com.webank.databrain.vo.common;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotDataRequest extends CommonRequest{
    private int topCount = 10;
}
