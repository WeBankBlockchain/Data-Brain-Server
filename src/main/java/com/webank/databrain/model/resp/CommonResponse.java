package com.webank.databrain.model.resp;

import com.webank.databrain.enums.ErrorEnums;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private T data;


    public CommonResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<T>(ErrorEnums.Success.getCode(), StringUtils.EMPTY, data);
    }

    public static<T> CommonResponse<T> fail(String code, String msg) {
        return new CommonResponse<T>(code, msg, null);
    }


}
