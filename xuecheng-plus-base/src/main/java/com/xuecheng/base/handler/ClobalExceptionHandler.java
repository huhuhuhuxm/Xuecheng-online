package com.xuecheng.base.handler;

import com.xuecheng.base.constant.CommonError;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class ClobalExceptionHandler {

    //对项目自定义异常进行处理
    @ExceptionHandler(XueChengPlusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(XueChengPlusException e) {

        //记录异常
        log.error("系统异常{}",e.getErrMessage(),e);
        //解析异常信息
        String errMessage = e.getErrMessage();
        return new RestErrorResponse(errMessage);
    }


    //捕获其他异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse exception(Exception e) {
        //记录异常
        log.error("系统异常{}",e.getMessage(),e);
        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
    }

    //解析JSR303校验
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(item -> errors.add(item.getDefaultMessage()));
        //将list中的错误信息拼接起来
        String errMessage = StringUtils.join(errors, ",");

        return new RestErrorResponse(errMessage);
    }

}
