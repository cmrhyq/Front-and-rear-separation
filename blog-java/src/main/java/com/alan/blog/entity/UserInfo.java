package com.alan.blog.entity;

import lombok.Data;

/**
 * <p>用户信息实体类</p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className UserInfo.java
 * @project blog
 * @package com.alan.blog.entity
 * @date 2021/9/17-11:23
 * @email cmrhyq@163.com
 */
@Data
public class UserInfo {
    /**
     * id
     */
    private Integer userInfoId;
    /**
     * 昵称
     */
    private String userInfoNick;
    /**
     * 姓名
     */
    private String userInfoName;
    /**
     * 性别
     */
    private String userInfoSex;
    /**
     * 年龄
     */
    private int userInfoAge;
    /**
     * 证件号
     */
    private String userInfoIdNumber;
    /**
     * 证件类型
     */
    private String userInfoIdType;
    /**
     * 邮箱
     */
    private String userInfoEmail;
    /**
     * 所在省市
     */
    private String userInfoProvince;
    /**
     * 详细地址
     */
    private String userInfoAddress;
    /**
     * userLogin 表 Phone
     */
    private Integer userLoginId;
}
