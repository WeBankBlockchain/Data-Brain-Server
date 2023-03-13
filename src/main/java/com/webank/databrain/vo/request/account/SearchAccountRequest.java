package com.webank.databrain.vo.request.account;

import com.webank.databrain.vo.common.CommonPageQueryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchAccountRequest extends CommonPageQueryRequest {
    @Min(value = 0, message = "账户状态不正确.")
    private int accountStatus;
}
