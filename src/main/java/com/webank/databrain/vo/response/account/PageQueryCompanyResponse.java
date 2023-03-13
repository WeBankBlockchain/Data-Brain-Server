package com.webank.databrain.vo.response.account;

import com.webank.databrain.model.resp.BasePageQueryResult;
import com.webank.databrain.model.resp.PagedResult;
import com.webank.databrain.vo.common.PageListData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PageQueryCompanyResponse extends PageListData<CompanyInfoResponse> {



}
