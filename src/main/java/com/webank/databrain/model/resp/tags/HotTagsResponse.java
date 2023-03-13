package com.webank.databrain.model.resp.tags;

import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.model.resp.BaseHotResponse;
import com.webank.databrain.vo.response.tag.TagIdAndNameResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HotTagsResponse extends BaseHotResponse<TagIdAndNameResponse> {
    public HotTagsResponse(List<TagIdAndNameResponse> idNames) {
        super(idNames);
    }
}
