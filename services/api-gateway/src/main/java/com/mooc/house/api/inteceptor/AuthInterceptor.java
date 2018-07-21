/**
 * 
 */
package com.mooc.house.api.inteceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.google.common.base.Joiner;
import com.mooc.house.api.common.CommonConstants;
import com.mooc.house.api.common.UserContext;
import com.mooc.house.api.dao.UserDao;
import com.mooc.house.api.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;

/**
 *第一个拦截器，对所有请求都拦截，作用：
 *
 *1.对静态资源访问直接放行
 *2.对动态资源，如果cookie中能获取token表示已登录，则将user信息写入到threadlocal，不管知否登录都继续放行
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
  
  private static final String TOKEN_COOKIE = "token";
  
  
  @Autowired
  private UserDao userDao;

  
  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
          throws Exception {
    Map<String, String[]> map = req.getParameterMap();
    map.forEach((k,v) ->req.setAttribute(k, Joiner.on(",").join(v)));
    String requestURI = req.getRequestURI();
    if (requestURI.startsWith("/static") || requestURI.startsWith("/error")) {
      return true;
    }
    Cookie cookie = WebUtils.getCookie(req, TOKEN_COOKIE);
    if (cookie != null && StringUtils.isNoneBlank(cookie.getValue())) {
        User user = userDao.getUserByToken(cookie.getValue());
        if (user != null) {
        	
        	req.setAttribute(CommonConstants.LOGIN_USER_ATTRIBUTE, user);
//          req.setAttribute(CommonConstants.USER_ATTRIBUTE, user);
        	UserContext.setUser(user);
        }
    }
    return true;
  }
  

  /**
   * 视图返回前，更新cookie并返回
   */
  @Override
  public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
          ModelAndView modelAndView) throws Exception {
    String requestURI = req.getRequestURI();
    if (requestURI.startsWith("/static") || requestURI.startsWith("/error")) {
      return ;
    }
    User user = UserContext.getUser();
    if (user != null && StringUtils.isNoneBlank(user.getToken())) {
       String token = requestURI.startsWith("logout")? "" : user.getToken();
       Cookie cookie = new Cookie(TOKEN_COOKIE, token);
       cookie.setPath("/");
       cookie.setHttpOnly(false);
       res.addCookie(cookie);
    }
    
  }
  
  

  @Override
  public void afterCompletion(HttpServletRequest req, HttpServletResponse response, Object handler, Exception ex)
          throws Exception {
    UserContext.remove();
  }
}
