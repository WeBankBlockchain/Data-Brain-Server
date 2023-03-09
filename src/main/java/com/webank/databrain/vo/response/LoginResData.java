package com.webank.databrain.vo.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginResData {
    private String token;
}
