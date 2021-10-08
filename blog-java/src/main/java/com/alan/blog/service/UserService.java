package com.alan.blog.service;

import com.alan.blog.entity.SystemKey;
import com.alan.blog.entity.UserLogin;
import com.alan.blog.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Map;

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
     * 根据手机号查询用户的所有信息
     *
     * @param userPhone 手机号
     * @return 用户组合信息
     */
    Map<String, Object> queryInfoAsUserPhone(String userPhone);

    /**
     * 登录
     *
     * @param userPhone-手机号
     * @param userPassword-密码
     * @return 登录结果
     * @throws Exception
     */
    Result login(String userPhone, String userPassword) throws Exception;

    /**
     * 用户账号控制信息
     * @return List<Map<String, String>>
     */
    List<Map<String, Object>>  controllerUser();
}
