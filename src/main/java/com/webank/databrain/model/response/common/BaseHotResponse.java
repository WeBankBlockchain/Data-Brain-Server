package com.webank.databrain.model.response.common;

import com.webank.databrain.model.vo.common.IdName;

import java.util.ArrayList;
import java.util.Collection;

public class BaseHotResponse extends ArrayList<IdName> {

    public BaseHotResponse(Collection<IdName> idNames){
        super(idNames);
    }

}
