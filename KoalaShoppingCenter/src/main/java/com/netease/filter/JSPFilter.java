package com.netease.filter;

import javax.servlet.Filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netease.meta.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Muyuxi
 * @Date 2019/2/26
 * @Describtion
 */
public class JSPFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(JSPFilter.class);
    private List<String> noCheckList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化fiter");
        String noChecks = filterConfig.getInitParameter("noCheck");
        if(noChecks != null && noChecks.length()>0 && noChecks.trim().length()>0) {
            String[] urls = noChecks.split(",");
            for (String url : urls) {
                noCheckList.add(url);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        //对于index.jsp以及commodityInfo.jsp不进行过滤
        logger.info("过滤HTTP请求的URI {}",uri);
        if(!check(uri)){  //请求地址黑名单
            //如果session中不存在登录的user 则进行页面重定向
            User user = (User) request.getSession().getAttribute("user");
            if(user == null) {
                response.sendRedirect("/KoalaShoppingCenter/index.jsp");
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        logger.info("销毁filter");
    }

    //检查URL是否在白名单中
    private boolean check(String url) {
        if(noCheckList == null || noCheckList.size()<=0) return false;
        for (String s : noCheckList) {
            if(url.indexOf(s)>-1) return true;
        }
        return false;
    }

}
