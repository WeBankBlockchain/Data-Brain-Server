package com.webank.databrain.model.vo.account;

import lombok.Data;

//{\"name\":\"aaa\",\"contact\":\"bbb\",\"email\":\"ddd\",\"certType\":\"eee\",\"certNum\":\"fff\"}
@Data
public class NormalUserDetail {

    private String name;

    private String contact;

    private String email;

    private String certType;

    private String certNum;

}
