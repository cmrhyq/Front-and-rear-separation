package com.alan.blog.service.impl;

import com.alan.blog.ecc.BASE64Encoder;
import com.alan.blog.ecc.EccUtils;
import com.alan.blog.entity.SystemKey;
import com.alan.blog.entity.UserInfo;
import com.alan.blog.entity.UserLogin;
import com.alan.blog.mapper.BlogMapper;
import com.alan.blog.service.SystemService;
import com.alan.blog.service.UserService;
import com.alan.blog.utils.*;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
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

//    JSONObject json = new JSONObject();
//    json.put("abc",1);

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private SystemService systemService;

    /**
     * 根据手机号查询用户的所有信息
     *
     * @param userPhone 手机号
     * @return 用户组合信息
     */
    @Override
    public Map<String, Object> queryInfoAsUserPhone(String userPhone) {
        return blogMapper.queryInfoAsUserPhone(userPhone);
    }

    /**
     * 登录
     *
     * @param userPhone    手机号
     * @param userPassword 密码
     * @return 登录结果和信息
     */
    @Override
    public Result login(String userPhone, String userPassword) throws Exception {
        // 返回的结果
        Result returnResult = new Result();
        // 需要更新的输入的存储中介实体类
        UserLogin updateUserResult = new UserLogin();
        // 最后登录时间
        Timestamp userLastTime = new Timestamp(System.currentTimeMillis());
        // 根据手机号查询数据
        UserLogin queryLoginInfo = blogMapper.login(userPhone);
        // 查询系统公钥
        SystemKey systemKey = systemService.querySystemKey();
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
                blogMapper.updateUserLoginInfo(updateUserResult);
                // 将结果返回
                returnResult.setCode(EnumErrorCode.ACC_FREEZE.getCode());
                returnResult.setMsg(EnumErrorCode.ACC_FREEZE.getMessage());
                returnResult.setStatus(EnumErrorCode.ACC_FREEZE.getStatus());
            } else {
                // 因为数据库中的用户密码是一次MD5加密然后再经过一次ECC加密
                // 所以将数据库中的公钥取出进行一次加密再进行比对
                String databasePwd = queryLoginInfo.getUserPassword();
                byte[] b = EccUtils.encrypt(userPassword.getBytes(StandardCharsets.UTF_8), systemKey.getPublicKey());
                String str = BASE64Encoder.encodeBuffer(b);
                if (!databasePwd.equals(str)) {
                    // 密码错误
                    returnResult.setCode(EnumErrorCode.ACC_PWD_ERR.getCode());
                    returnResult.setMsg(EnumErrorCode.ACC_PWD_ERR.getMessage());
                    returnResult.setStatus(EnumErrorCode.ACC_PWD_ERR.getStatus());
                } else {
                    UserInfo queryUserInfo = blogMapper.queryUserInfo(queryLoginInfo.getUserId());
                    // 最后登录时间和最后登录ip存入数据库
                    updateUserResult.setUserLastTime(userLastTime);
                    updateUserResult.setUserLastLoginIp(IPUtil.getIP(HttpContextUtil.getHttpServletRequest()));
                    updateUserResult.setUserId(queryLoginInfo.getUserId());
                    blogMapper.updateUserLoginInfo(updateUserResult);
                    // 生成token并返回结果和数据
                    Map<String, String> map = new HashMap<>();
                    List<Map<String, String>> list = new ArrayList<>();
                    map.put("userInfoPhone", queryLoginInfo.getUserPhone());
                    map.put("userInfoIp", queryLoginInfo.getUserLastLoginIp());
                    map.put("userInfoNick", queryUserInfo.getUserInfoNick());
                    map.put("userInfoName", queryUserInfo.getUserInfoName());
                    map.put("userInfoSex", queryUserInfo.getUserInfoSex());
                    map.put("userToken", TokenUtil.sing(userPhone));
                    list.add(map);
                    returnResult.setCode(EnumErrorCode.LOGIN_SUCCESS.getCode());
                    returnResult.setMsg(EnumErrorCode.LOGIN_SUCCESS.getMessage());
                    returnResult.setData(list);
                    returnResult.setStatus(EnumErrorCode.LOGIN_SUCCESS.getStatus());
                }
            }
        }
        return returnResult;
    }

    /**
     * 用户账号控制信息
     *
     * @return List<Map < String, String>>
     */
    @Override
    public List<Map<String, Object>> controllerUser() {
        return blogMapper.queryUserLoginAll();
    }

    /**
     * 更新用户信息
     *
     * @param userInfo 更新的信息
     * @return 更新结果
     */
    @Override
    public Result updateUserInfo(UserInfo userInfo) {
        // 返回的结果
        Result returnResult = new Result();
        int affectTotalRow = blogMapper.updateUserInfo(userInfo);
        if (affectTotalRow > 0) {
            returnResult.setCode(EnumErrorCode.SUCCESS.getCode());
            returnResult.setMsg("更新成功");
            returnResult.setStatus(EnumErrorCode.SUCCESS.getStatus());
        } else {
            returnResult.setCode(EnumErrorCode.FAIL.getCode());
            returnResult.setMsg("更新失败");
            returnResult.setStatus(EnumErrorCode.FAIL.getStatus());
        }
        return returnResult;
    }

    /**
     * 注册
     *
     * @param userPhone    手机号
     * @param userPassword 密码
     * @return 注册结果
     */
    @Override
    public Result insertUserLogin(String userPhone, String userPassword, HttpServletRequest request) throws Exception {
        // 返回的结果
        Result returnResult = new Result();
        // 根据手机号查询数据
        UserLogin queryLoginInfo = blogMapper.login(userPhone);
        // 查询系统公钥
        SystemKey systemKey = systemService.querySystemKey();
        // 判断这个账号是否注册
        if (queryLoginInfo == null) {
            // 未注册
            Map<String, Object> paramsMap = new HashMap<>();
            // 加密密码
            byte[] passEncrypt = EccUtils.encrypt(userPassword.getBytes(StandardCharsets.UTF_8), systemKey.getPublicKey());
            paramsMap.put("userPhone", userPhone);
            paramsMap.put("userPassword", BASE64Encoder.encodeBuffer(passEncrypt));
            paramsMap.put("userRegTime", new Timestamp(System.currentTimeMillis()));
            paramsMap.put("userStatus", 0);
            paramsMap.put("userSessionId", request.getSession().getId());
            try {
                Map<String, String> map = new HashMap<>();
                List<Map<String, String>> list = new ArrayList<>();
                // 受影响行数
                int affectTotalRow = blogMapper.insertUserLogin(paramsMap);
                log.info("受影响行数：" + affectTotalRow);

                map.put("userPhone", userPhone);
                list.add(map);

                returnResult.setCode(EnumErrorCode.REG_SUCCESS.getCode());
                returnResult.setMsg(EnumErrorCode.REG_SUCCESS.getMessage());
                returnResult.setData(list);
                returnResult.setStatus(EnumErrorCode.REG_SUCCESS.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
                returnResult.setCode(EnumErrorCode.ACC_REG_FAIL.getCode());
                returnResult.setMsg(EnumErrorCode.ACC_REG_FAIL.getMessage() + ":" + e.getMessage());
                returnResult.setStatus(EnumErrorCode.ACC_REG_FAIL.getStatus());
            }
        } else {
            // 已注册
            returnResult.setCode(EnumErrorCode.ACC_HAS_BEEN_REG.getCode());
            returnResult.setMsg(EnumErrorCode.ACC_HAS_BEEN_REG.getMessage());
            returnResult.setStatus(EnumErrorCode.ACC_HAS_BEEN_REG.getStatus());
        }
        return returnResult;
    }

    /**
     * 注册第二步个人信息填写
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    @Override
    public Result insertUserInfo(UserInfo userInfo, String userPhone) {
        // 返回的结果
        Result returnResult = new Result();
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            paramsMap.put("userInfoNick", userInfo.getUserInfoNick());
            paramsMap.put("userInfoName", userInfo.getUserInfoName());
            paramsMap.put("userInfoSex", userInfo.getUserInfoSex());
            paramsMap.put("userInfoAge", userInfo.getUserInfoAge());
            paramsMap.put("userInfoIdNumber", userInfo.getUserInfoIdNumber());
            paramsMap.put("userInfoIdType", userInfo.getUserInfoIdType());
            paramsMap.put("userInfoEmail", userInfo.getUserInfoEmail());
            paramsMap.put("userInfoProvince", userInfo.getUserInfoProvince());
            paramsMap.put("userInfoAddress", userInfo.getUserInfoAddress());
            paramsMap.put("userLoginId", blogMapper.login(userPhone).getUserId());
            Map<String, String> returnMap = new HashMap<>();
            List<Map<String, String>> list = new ArrayList<>();
            // 受影响行数
            int affectTotalRow = blogMapper.insertUserInfo(paramsMap);
            log.info("受影响行数：" + affectTotalRow);
            returnMap.put("userNick", userInfo.getUserInfoNick());
            list.add(returnMap);
            returnResult.setCode(EnumErrorCode.SUCCESS.getCode());
            returnResult.setMsg(EnumErrorCode.SUCCESS.getMessage());
            returnResult.setData(list);
            returnResult.setStatus(EnumErrorCode.SUCCESS.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            returnResult.setCode(EnumErrorCode.FAIL.getCode());
            returnResult.setMsg(EnumErrorCode.FAIL.getMessage() + ":" + e.getMessage());
            returnResult.setStatus(EnumErrorCode.FAIL.getStatus());
        }
        return returnResult;
    }

}
