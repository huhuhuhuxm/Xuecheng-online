package com.xuecheng.base.exception;

import com.xuecheng.base.constant.CommonError;

/**
 * 本项目自定义异常模型
 */
public class XueChengPlusException extends RuntimeException{
    private String errMessage;

    public XueChengPlusException() {
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public XueChengPlusException(String message) {
        super(message);
        this.errMessage = message;
    }

    public static void cast(String message) {
        throw new XueChengPlusException(message);
    }


    public static void cast(CommonError commonError) {
        throw new XueChengPlusException(commonError.getErrMessage());
    }

}
