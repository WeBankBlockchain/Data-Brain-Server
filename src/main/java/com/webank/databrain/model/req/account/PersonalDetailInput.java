package com.webank.databrain.model.req.account;

import lombok.Data;

//{\"name\":\"aaa\",\"contact\":\"bbb\",\"email\":\"ddd\",\"certType\":\"eee\",\"certNum\":\"fff\"}
@Data
public class PersonalDetailInput {

    private String name;

    private String contact;

    private String email;

    private String certType;

    private String certNum;

}
