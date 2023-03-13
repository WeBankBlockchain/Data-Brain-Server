package com.webank.databrain.vo.response.account;

import com.webank.databrain.model.resp.PagedResult;
import com.webank.databrain.vo.common.PageListData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SearchPersonResponse extends PageListData<PersonInfoResponse> {


}
