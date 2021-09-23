package com.alan.blog.service.impl;

import com.alan.blog.entity.UserInfo;
import com.alan.blog.entity.UserLogin;
import com.alan.blog.mapper.UserMapper;
import com.alan.blog.service.UserService;
import com.alan.blog.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Alan Huang·
 * @version v1.0.0
 * @className UserServiceImpl.java
 * @project blog
 * @package com.alan.blog.service
 * @date 2021/9/15-11:37
 * @email cmrhyq@163.com
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

//    request.getSession().getId()

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param userPhone
     * @param userPassword
     * @return
     */
    @Override
    public Result login(String userPhone, String userPassword) {
        // 返回的结果
        Result returnResult = new Result();
        // 需要更新的输入的存储中介实体类
        UserLogin updateUserResult = new UserLogin();
        // 最后登录时间
        Timestamp userLastTime = new Timestamp(System.currentTimeMillis());
        // 根据手机号查询数据
        UserLogin queryLoginInfo = userMapper.login(userPhone);
        UserInfo queryUserInfo = userMapper.queryUserInfo(queryLoginInfo.getUserId());
        if (queryLoginInfo == null) {
            // 账号未注册
            returnResult.setCode(EnumErrorCode.ACC_NO_REG.getCode());
            returnResult.setMsg(EnumErrorCode.ACC_NO_REG.getMessage());
            returnResult.setStatus(EnumErrorCode.ACC_NO_REG.getStatus());
        } else {
            // 账号冻结
            if (queryLoginInfo.getUserStatus() == 0) {
                // 最后登录时间和最后登录ip存入数据库
                updateUserResult.setUserLastTime(userLastTime);
                updateUserResult.setUserLastLoginIp(IPUtil.getIP(HttpContextUtil.getHttpServletRequest()));
                updateUserResult.setUserId(queryLoginInfo.getUserId());
                userMapper.updateUserInfo(updateUserResult);
                // 将结果返回
                returnResult.setCode(EnumErrorCode.ACC_FREEZE.getCode());
                returnResult.setMsg(EnumErrorCode.ACC_FREEZE.getMessage());
                returnResult.setStatus(EnumErrorCode.ACC_FREEZE.getStatus());
            } else {
                // 密码错误
                if (!queryLoginInfo.getUserPassword().equals(userPassword)) {
                    returnResult.setCode(EnumErrorCode.ACC_PWD_ERR.getCode());
                    returnResult.setMsg(EnumErrorCode.ACC_PWD_ERR.getMessage());
                    returnResult.setStatus(EnumErrorCode.ACC_PWD_ERR.getStatus());
                } else {
                    // 最后登录时间和最后登录ip存入数据库
                    updateUserResult.setUserLastTime(userLastTime);
                    updateUserResult.setUserLastLoginIp(IPUtil.getIP(HttpContextUtil.getHttpServletRequest()));
                    updateUserResult.setUserId(queryLoginInfo.getUserId());
                    userMapper.updateUserInfo(updateUserResult);
                    // 生成token并返回结果和数据
                    Map<String, String> map = new HashMap<>();
                    List<Map<String, String>> list = new ArrayList<>();
                    map.put("userInfoPhone", queryLoginInfo.getUserPhone());
                    map.put("userInfoIp", queryLoginInfo.getUserLastLoginIp());
                    map.put("userInfoNick", queryUserInfo.getUserInfoNick());
                    map.put("userInfoName", queryUserInfo.getUserInfoName());
                    map.put("userInfoSex", queryUserInfo.getUserInfoSex());
                    list.add(map);
                    returnResult.setCode(EnumErrorCode.LOGIN_SUCCESS.getCode());
                    returnResult.setMsg(EnumErrorCode.LOGIN_SUCCESS.getMessage());
                    returnResult.setList(list);
                    returnResult.setToken(TokenUtil.sing(userPhone));
                    returnResult.setStatus(EnumErrorCode.LOGIN_SUCCESS.getStatus());
                }
            }
        }
        return returnResult;
    }
}