package com.webank.databrain.error;

import com.webank.databrain.enums.ErrorEnums;
import lombok.Getter;

@Getter
public class DataBrainException extends RuntimeException{
    private ErrorEnums error;
    public DataBrainException(ErrorEnums error){
        super(error.getMessage());
    }

}
