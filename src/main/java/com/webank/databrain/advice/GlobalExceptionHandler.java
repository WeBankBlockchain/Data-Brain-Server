package com.webank.databrain.advice;

import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.error.DataBrainException;
import com.webank.databrain.model.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DataBrainException.class)
    @ResponseBody
    public CommonResponse onException(DataBrainException exception){
        log.error("OnError: DataBrainException Exception happened {}", exception);
        ErrorEnums errorEnums = exception.getError();
        CommonResponse resp = CommonResponse.createFailedResult(errorEnums.getCode(), errorEnums.getMessage());
        log.info("response is {}", resp);
        return resp;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse onException(Exception exception){
        log.error("OnError: Exception happened {}", exception);
        ErrorEnums errorEnums = ErrorEnums.UnknownError;
        CommonResponse resp = CommonResponse.createFailedResult(errorEnums.getCode(), exception.getMessage());
        log.info("response is {}", resp);
        return resp;
    }

}
