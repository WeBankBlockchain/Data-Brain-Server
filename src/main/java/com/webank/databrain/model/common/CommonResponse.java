package com.webank.databrain.model.common;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;


    public CommonResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResponse<T> createSuccessResult(T data) {
        return new CommonResponse<T>(200, StringUtils.EMPTY, data);
    }

    public static<T> CommonResponse<T> createFailedResult(int code, String msg) {
        return new CommonResponse<T>(code, msg, null);
    }
}
