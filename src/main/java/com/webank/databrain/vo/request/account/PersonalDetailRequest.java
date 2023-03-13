package com.webank.databrain.vo.request.account;

import lombok.Data;

//{\"name\":\"aaa\",\"contact\":\"bbb\",\"email\":\"ddd\",\"certType\":\"eee\",\"certNum\":\"fff\"}
@Data
public class PersonalDetailRequest {

    private String name;

    private String contact;

    private String email;

    private String certType;

    private String certNum;

}
