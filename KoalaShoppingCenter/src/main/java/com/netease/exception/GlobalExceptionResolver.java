package com.netease.exception;

import com.netease.meta.ResponseMessage;
import com.netease.meta.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Muyuxi
 * @Date 2019/2/26
 * @Describtion 全局异常处理器
 * 通过 @ControllerAdvice注解，我们对所有@Controller注解的控制器进行管理。
 * 注解了@ControllerAdvice的类的方法可以使用 @ExceptionHandler、 @InitBinder、 @ModelAttribute 注解到方法上，这对所有注解了@RequestMapping 的控制器内的方法都有作用
 */

@ControllerAdvice
public class GlobalExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    /**
     * @Author Muyuxi
     * @Description 处理所有未知的异常
     * @Date 2019/2/26
     * @return com.netease.meta.ResponseMessage
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handlerException(Exception e) {
        logger.error(e.getMessage(),e); //将系统内部异常写入日志文件中
        return ResponseMessage.setStatus(ResultCode.UNKNOWN_ERROR);  //屏蔽系统内部异常,传递给用户不关于系统内部的异常信息
    }

    /**
     * @Author Muyuxi
     * @Description 处理业务异常
     * @Date 2019/2/26
     * @return com.netease.meta.ResponseMessage
     */
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public ResponseMessage handlerSystemException(SystemException e) {
        logger.error(e.getMessage());
        return ResponseMessage.setStatus(e.getResultCode());
    }

}
