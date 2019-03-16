package com.netease.meta;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Muyuxi
 * @Date 2019/2/25
 * @Describtion
 */
public class ResponseMessage {
    private Integer code;
    private String message;
    private Map<String,Object> res = new HashMap<>();  //用户返回给浏览器的数据

    public static ResponseMessage setStatus(ResultCode resultCode) {
        ResponseMessage result = new ResponseMessage();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    public ResponseMessage add(String key,Object value) {
        this.getRes().put(key,value);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getRes() {
        return res;
    }

    public void setRes(Map<String, Object> res) {
        this.res = res;
    }
}
