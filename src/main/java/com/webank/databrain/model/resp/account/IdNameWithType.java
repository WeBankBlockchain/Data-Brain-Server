package com.webank.databrain.model.resp.account;

import com.webank.databrain.model.resp.IdName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IdNameWithType extends IdName {

    private int accountType;

}
