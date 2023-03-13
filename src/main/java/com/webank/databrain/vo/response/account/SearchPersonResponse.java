package com.webank.databrain.vo.response.account;

import com.webank.databrain.model.resp.PagedResult;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchPersonResponse {

    private PagedResult<PersonInfoVO> result;

}
