package com.alan.blog.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <p>获取request</p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className HttpContextUtil.java
 * @project blog
 * @package com.alan.blog.utils
 * @date 2021/9/18-10:05
 * @email cmrhyq@163.com
 */
public class HttpContextUtil {

    private HttpContextUtil() {
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}