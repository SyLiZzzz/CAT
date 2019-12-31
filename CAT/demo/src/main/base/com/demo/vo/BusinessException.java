package com.demo.vo;

import com.demo.em.ErrorMessageEnum;

public class BusinessException extends RuntimeException{
    private int resultCode;
    private String message;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
//    枚举定义的错误消息
    public BusinessException(ErrorMessageEnum errorMessageEnum){
        this.resultCode = errorMessageEnum.getCode();
        this.message = errorMessageEnum.getMesg();
    }
//    自定义的错误消息
    public BusinessException(int resultCode,String message){
        this.resultCode = resultCode;
        this.message = message;
    }
}
