package com.alan.blog.entity;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Timestamp getUserRegTime() {
        return userRegTime;
    }

    public void setUserRegTime(Timestamp userRegTime) {
        this.userRegTime = userRegTime;
    }

    public Timestamp getUserLastTime() {
        return userLastTime;
    }

    public void setUserLastTime(Timestamp userLastTime) {
        this.userLastTime = userLastTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getUserLastLoginIp() {
        return userLastLoginIp;
    }

    public void setUserLastLoginIp(String userLastLoginIp) {
        this.userLastLoginIp = userLastLoginIp;
    }
}
