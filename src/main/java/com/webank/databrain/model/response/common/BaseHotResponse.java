package com.webank.databrain.model.response.common;

import com.webank.databrain.model.dto.common.IdName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseHotResponse<T>{
    protected List<T> items;
}
