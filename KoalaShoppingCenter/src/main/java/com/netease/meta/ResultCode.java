package com.netease.meta;

/**
 * @Author Muyuxi
 * @Date 2019/2/26
 * @Describtion 返回结果码ResultCode
 */
public enum ResultCode {

    //成功
    SUCCESS(0, "success"),

    //未知错误
    UNKNOWN_ERROR(1, "unkonwn error"),

    //用户名或密码错误
    USER_ERROR(2, "username or password error or does not exist"),

    //数据库异常
    DATABASE_ERROR(3,"database exception"),

    //请求参数缺失
    PARAM_MISSERROE(4,"Request parameter missing"),

    //请求格式错误
    DATA_FORMAT_ERROR(5,"data with wrong format"),

    //数据操作异常
    DATA_OPERATION_ERROR(6,"data operation error"),

    //查询信息不存在
    DATA_NOTEXISTS_ERROR(7,"infomation not exist"),

    //添加购物车失败
    ADD_SHOPPINGCART_ERROR(8,"add to shoppingCart failure"),

    //从购物车中删除商品失败
    DEL_SHOPPINGCART_ERROR(9,"delete shoppingCart commodity failure"),

    //购买商品失败
    PURCHASE_ERROR(10,"pruchase commodity failure"),

    //商品已售出不能删除
    DEL_ERROR(11,"商品已售出不能删除"),

    //商品达到最大发布数量
    MAX_Commodity(12,"每一名商家最多只能发布1000个内容");

    private Integer code;

    private String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
