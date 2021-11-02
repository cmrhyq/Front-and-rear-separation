package com.alan.blog.controller;

import com.alan.blog.entity.UserInfo;
import com.alan.blog.entity.UserLogin;
import com.alan.blog.service.UserService;
import com.alan.blog.utils.EnumErrorCode;
import com.alan.blog.utils.Result;
import com.alan.blog.utils.TokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className UserController.java
 * @project blog
 * @package com.alan.blog.controller
 * @date 2021/9/15-11:36
 * @email cmrhyq@163.com
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据手机号查询用户的所有信息
     *
     * @param user 手机号
     * @return 用户信息
     */
    @ResponseBody
    @PostMapping("/queryInfoAsPhone")
    public Map<String, Object> queryInfoAsPhone(@RequestBody UserLogin user) {
        return userService.queryInfoAsUserPhone(user.getUserPhone());
    }

    /**
     * 登录
     *
     * @param user 登录信息
     * @return 登录结果和信息
     * @throws JsonProcessingException
     */
    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestBody UserLogin user) throws Exception {
        return userService.login(user.getUserPhone(), user.getUserPassword());
    }

    /**
     * 注册
     *
     * @param user    登录信息
     * @param request
     * @return 注册结果
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/register")
    public Result register(@RequestBody UserLogin user, HttpServletRequest request) throws Exception {
        return userService.insertUserLogin(user.getUserPhone(), user.getUserPassword(), request);
    }

    /**
     * 个人信息填写
     *
     * @param userInfo 个人信息
     * @param userLogin 手机号的信息
     * @return 增加结果
     */
    @ResponseBody
    @PostMapping("/addUserInfo")
    public Result addUserInfo(@RequestBody UserInfo userInfo, @RequestBody UserLogin userLogin){
        return userService.insertUserInfo(userInfo, userLogin.getUserPhone());
    }

    /**
     * 用户账号控制信息
     *
     * @return List<Map < String, String>>
     */
    @ResponseBody
    @PostMapping("/table/controllerUser")
    public List<Map<String, Object>> controllerUser() {
        return userService.controllerUser();
    }

    /**
     * 更新用户信息
     *
     * @param userInfo 用户喜喜
     * @return 更新结果
     */
    @ResponseBody
    @PostMapping("/edit/userInfo")
    public Result editUserInfo(@RequestBody UserInfo userInfo) {
        log.info("userInfo:" + userInfo);
        return userService.updateUserInfo(userInfo);
    }

    /**
     * token 测试demo
     *
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/test")
    public Result test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        // 返回的结果
        Result returnResult = new Result();
        returnResult.setCode(EnumErrorCode.LOGIN_SUCCESS.getCode());
        returnResult.setMsg("校验结果为：" + TokenUtil.verify(token));
        returnResult.setStatus(EnumErrorCode.LOGIN_SUCCESS.getStatus());
        return returnResult;
    }
}
