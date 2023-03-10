package com.webank.databrain.model.resp.account;

import com.webank.databrain.model.resp.PagedResult;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchPersonResponse {

    private PagedResult<PersonInfoVO> result;

}
