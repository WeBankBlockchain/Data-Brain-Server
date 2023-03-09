package com.webank.databrain.advice;

import com.webank.databrain.enums.CodeEnum;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.exception.DataBrainException;
import com.webank.databrain.model.resp.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public com.webank.databrain.vo.common.CommonResponse handleMethodArgumentNotValidException(HttpServletRequest req, Exception e){
        MethodArgumentNotValidException exception = (MethodArgumentNotValidException)e;
        FieldError fe = exception.getFieldError();
        String debugRtnMsg = CodeEnum.PARAMETER_ERROR.getMsg() + ":" + fe.getDefaultMessage();
        return com.webank.databrain.vo.common.CommonResponse.error(CodeEnum.PARAMETER_ERROR.getCode(), debugRtnMsg);
    }

    @ExceptionHandler(DataBrainException.class)
    @ResponseBody
    public CommonResponse onException(DataBrainException exception){
        log.error("OnError: DataBrainException Exception happened", exception);
        ErrorEnums errorEnums = exception.getError();
        CommonResponse resp = CommonResponse.fail(errorEnums.getCode(), errorEnums.getMessage());
        log.info("response is {}", resp);
        return resp;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse onException(Exception exception){
        log.error("OnError: Exception happened ", exception);
        ErrorEnums errorEnums = ErrorEnums.UnknownError;
        CommonResponse resp = CommonResponse.fail(errorEnums.getCode(), exception.getMessage());
        log.info("response is {}", resp);
        return resp;
    }

}
