package com.netease.service;

import com.netease.dao.UserOperationMapper;
import com.netease.exception.SystemException;
import com.netease.meta.ResultCode;
import com.netease.meta.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * @Author Muyuxi
 * @Date 2019/2/25
 * @Describtion
 */
@Service
public class UserOperationService {

    @Autowired
    private UserOperationMapper userOperationMapper;
    private static Logger logger = LoggerFactory.getLogger(UserOperationService.class);

    //异常处理 与日志
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public User login(User u) {
        User user = userOperationMapper.findUser(u);
        if(user == null){
            throw new SystemException(ResultCode.USER_ERROR);
        }
        logger.info("用户 {} 登陆成功",user.getUserName());
        return user;
    }




}
