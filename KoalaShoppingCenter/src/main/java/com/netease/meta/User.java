package com.netease.meta;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;

/**
 * @Author Muyuxi
 * @Date 2019/2/24
 */
@Component
public class User {
    private Integer userID;    //用户ID

    @Pattern(regexp ="(^[a-z0-9_-]{5,10}$)|(^[\u2E80-\u9FFF]{2,5})",message = "用户名必须是5到10位的英文或者2到5位的中文")
    private String userName;   //用户登录名

    @Pattern(regexp ="(^(?=.*[a-z])[A-Za-z\\d]{5,}$)",message = "至少5位 且包含一个小写字母")
    private String password;   //用户密码
    private Integer userType;  //用户类型
    public User() {

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        String type = userType==0?"买家":"卖家";
        return "userName: "+userName+" "+type;
    }
}
