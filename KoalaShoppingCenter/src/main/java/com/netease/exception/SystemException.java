package com.netease.exception;

import com.netease.meta.ResultCode;

/**
 * @Author Muyuxi
 * @Date 2019/2/25
 */
public class SystemException extends RuntimeException {
    private Integer code;  //状态码
    private String message; //状态消息
    private ResultCode resultCode; //结果码枚举


    public SystemException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.resultCode = resultCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
