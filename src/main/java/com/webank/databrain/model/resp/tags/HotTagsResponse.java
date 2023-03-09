package com.webank.databrain.model.resp.tags;

import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.model.resp.BaseHotResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HotTagsResponse extends BaseHotResponse<IdName> {
    public HotTagsResponse(List<IdName> idNames) {
        super(idNames);
    }
}
