package com.alan.blog.mapper;

import com.alan.blog.entity.SystemKey;
import com.alan.blog.entity.UserInfo;
import com.alan.blog.entity.UserLogin;

import javax.persistence.MapKey;
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
public interface BlogMapper {

    /**
     * 根据手机号查询用户的所有信息
     *
     * @param userPhone 手机号
     * @return 用户组合信息
     */
    Map<String, Object> queryInfoAsUserPhone(String userPhone);

    /**
     * 查询所有用户登录信息
     *
     * @return 用户信息
     */
    List<Map<String, Object>> queryUserLoginAll();

    /**
     * 根据手机号查询用户信息
     *
     * @param userPhone-手机号
     * @return 用户信息
     */
    UserLogin login(String userPhone);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     * @return 受影响行数
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 更新用户登录信息
     *
     * @param userLogin-用户信息
     */
    void updateUserLoginInfo(UserLogin userLogin);

    /**
     * 查询用户信息
     *
     * @param userLoginId-用户id
     * @return 用户信息
     */
    UserInfo queryUserInfo(int userLoginId);

    /**
     * 查询系统密钥对
     *
     * @return 密钥对
     */
    SystemKey querySystemKey();

    /**
     * 新增系统密钥对
     *
     * @param map-密钥对
     */
    void insertSystemKey(Map<String, String> map);

    /**
     * 更新系统密钥对
     *
     * @param map-密钥对
     */
    void updateSystemKey(Map<String, String> map);
}
