package com.webank.databrain.model.account;

import lombok.Data;

//{\"name\":\"aaa\",\"contact\":\"bbb\",\"location\":\"ccc\",\"email\":\"ddd\",\"certType\":\"eee\",\"certNum\":\"fff\"}
@Data
public class NormalUserDetail {

    private String name;

    private String contact;

    private String location;

    private String email;

    private String certType;

    private String certNum;

}
