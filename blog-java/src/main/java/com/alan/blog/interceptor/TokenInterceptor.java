package com.alan.blog.interceptor;

import com.alan.blog.utils.EnumErrorCode;
import com.alan.blog.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>拦截器</p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className TokenInterceptor.java
 * @project blog
 * @package com.alan.blog.interceptor
 * @date 2021/9/18-16:52
 * @email cmrhyq@163.com
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 拦截器
     *
     * @param request  - request
     * @param response - response
     * @param handler  - handler
     * @return boolean - true or false
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String str = "OPTIONS";
        if (str.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        // 获取token
        String token = request.getHeader("token");
        if (token != null) {
            boolean result = TokenUtil.verify(token);
            if (result) {
                log.info("认证成功，通过拦截器");
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            JSONObject json = new JSONObject();
            json.put("msg", EnumErrorCode.AUTHEN_FAIL.getMessage());
            json.put("code", EnumErrorCode.AUTHEN_FAIL.getStatus());
            response.getWriter().append("" + json);
            log.info("认证结果:认证失败，未通过拦截器");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}
