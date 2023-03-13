package com.webank.databrain.vo.request.account;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ApproveAccountRequest {
    @NotBlank(message = "用户名不能为空.")
    private String did;
    private boolean approved;
}

