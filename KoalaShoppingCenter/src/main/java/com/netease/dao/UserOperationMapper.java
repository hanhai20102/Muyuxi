package com.netease.dao;

import com.netease.meta.User;

/**
 * @Author Muyuxi
 * @Date 2019/2/25
 * @Describtion 关于用户操作的一些接口
 */
public interface UserOperationMapper {

    User findUser(User user);


}
