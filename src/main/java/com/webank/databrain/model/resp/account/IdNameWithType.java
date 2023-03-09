package com.webank.databrain.model.resp.account;

import com.webank.databrain.model.resp.IdName;
import lombok.Data;

@Data
public class IdNameWithType extends IdName {

    private int accountType;

}
