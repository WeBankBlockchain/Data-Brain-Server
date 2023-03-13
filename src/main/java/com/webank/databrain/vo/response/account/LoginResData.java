package com.webank.databrain.vo.response.account;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginResData {
    private String token;
}
