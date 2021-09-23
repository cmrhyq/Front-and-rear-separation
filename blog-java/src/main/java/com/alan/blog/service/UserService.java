package com.alan.blog.service;

import com.alan.blog.entity.UserLogin;
import com.alan.blog.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className UserService.java
 * @project blog
 * @package com.alan.blog.service
 * @date 2021/9/15-11:38
 * @email cmrhyq@163.com
 */
public interface UserService {

    /**
     * 登录
     *
     * @param userPhone
     * @param userPassword
     * @return
     */
    Result login(String userPhone, String userPassword);

}
