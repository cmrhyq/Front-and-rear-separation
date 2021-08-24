package com.alan.demo.entity;

import lombok.Data;

/**
 * <p>User表实体类</p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className User.java
 * @project demoProject
 * @package com.alan.demo.entity
 * @date 2021/8/21-21:04
 * @email cmrhyq@163.com
 */
@Data
public class User {
    /**
     * id
     */
    private int id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 城市
     */
    private String city;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 微信
     */
    private String wechat;
    /**
     * qq
     */
    private String qq;
    /**
     * 微博
     */
    private String weibo;
    /**
     * 性别
     */
    private String sex;
    /**
     * 简介
     */
    private String description;
}
