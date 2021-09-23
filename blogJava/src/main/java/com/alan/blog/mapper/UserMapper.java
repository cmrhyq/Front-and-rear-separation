package com.alan.blog.mapper;

import com.alan.blog.entity.UserInfo;
import com.alan.blog.entity.UserLogin;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className UserMapper.java
 * @project blog
 * @package com.alan.blog.mapper
 * @date 2021/9/15-11:23
 * @email cmrhyq@163.com
 */
public interface UserMapper {

    /**
     * 登录
     *
     * @param userPhone
     * @return
     */
    UserLogin login(String userPhone);

    /**
     * 更新账号状态
     *
     * @param userLogin
     */
    void updateUserInfo(UserLogin userLogin);

    /**
     * 查询用户信息
     *
     * @param userLoginId
     * @return
     */
    UserInfo queryUserInfo(int userLoginId);
}
