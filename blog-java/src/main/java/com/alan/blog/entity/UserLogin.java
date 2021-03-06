package com.alan.blog.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className UserLogin.java
 * @project blog
 * @package com.alan.blog.entity
 * @date 2021/9/15-17:53
 * @email cmrhyq@163.com
 */
@Data
public class UserLogin {

    /**
     * id
     */
    private Integer userId;
    /**
     * 手机号
     */
    private String userPhone;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 注册时间
     */
    private Timestamp userRegTime;
    /**
     * 最后登录时间
     */
    private Timestamp userLastTime;
    /**
     * 帐号状态
     */
    private Integer userStatus;
    /**
     * Session id
     */
    private String userSessionId;
    /**
     * 最后登录ip
     */
    private String userLastLoginIp;

}
