package com.webank.databrain.model.output.tags;

import com.webank.databrain.model.dto.common.IdName;
import com.webank.databrain.model.output.BaseHotResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class HotTagsResponse extends BaseHotResponse<IdName> {
    public HotTagsResponse(List<IdName> idNames) {
        super(idNames);
    }
}
