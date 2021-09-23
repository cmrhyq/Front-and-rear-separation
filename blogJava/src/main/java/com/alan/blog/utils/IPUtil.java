package com.alan.blog.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获得用户的请求的IP是多少
 * @author Alan Huang
 */
public class IPUtil {
    public static String getIP(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        String headerIP = request.getHeader("x-real-ip");
        if(headerIP == null || "".equals(headerIP) || "null".equals(headerIP)){
            headerIP = request.getHeader("x-forwarded-for");
        }
        if(headerIP !=null && !"".equals(headerIP) && !"null".equals(headerIP)){
            ip = headerIP;
        }
        return ip;
    }

}
