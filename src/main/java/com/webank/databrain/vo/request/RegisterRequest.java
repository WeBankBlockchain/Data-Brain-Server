package com.webank.databrain.vo.request;

import com.webank.databrain.vo.common.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterRequest extends CommonRequest {
    @NotBlank(message = "用户名不能为空.")
    private String userName;
    @NotBlank(message = "密码不能为空.")
    private String password;
    private int accountType;  // 1-普通用户；2-企业用户
    private String detailJson;
}
