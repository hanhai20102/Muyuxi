package com.netease.controller;

import com.netease.meta.ResponseMessage;
import com.netease.meta.ResultCode;
import com.netease.meta.User;
import com.netease.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Muyuxi
 * @Date 2019/2/24
 */

@Controller
public class UserOperationController {
    @Autowired
    private UserOperationService userOperationService;
    @RequestMapping("/login")
    @ResponseBody
    public ResponseMessage login(@Valid User u, BindingResult result, HttpServletRequest request, HttpSession session) {
        System.out.println("登录的用户名为："+u.getUserName());
        System.out.println("密码为："+u.getPassword());
        //做一个用户数据的校验
        if(result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
            Map<String,Object> errorMap = new HashMap<>();
            for(FieldError fieldError :errors){
                System.out.println("错误的字段名"+fieldError.getField());
                System.out.println("错误信息"+fieldError.getDefaultMessage());
                errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return ResponseMessage.setStatus(ResultCode.DATA_FORMAT_ERROR).add("errorField",errorMap);
        }else {
            User user = userOperationService.login(u);
            session = request.getSession();
            session.setAttribute("user",user);
            return ResponseMessage.setStatus(ResultCode.SUCCESS).add("user",user);
        }

    }

    @RequestMapping("/quit")
    public void quit(HttpSession session, HttpServletResponse response,HttpServletRequest request) throws IOException {

        //清楚保存在session中的数据
        session.removeAttribute("user");
        response.sendRedirect(request.getContextPath()+"/view/login.jsp");
    }
}
