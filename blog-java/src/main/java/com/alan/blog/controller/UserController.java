package com.alan.blog.controller;

import com.alan.blog.entity.UserLogin;
import com.alan.blog.service.UserService;
import com.alan.blog.utils.Result;
import com.alan.blog.utils.TokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
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
     * 登录
     *
     * @param user
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestBody UserLogin user) throws JsonProcessingException {
        return userService.login(user.getUserPhone(), user.getUserPassword());
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
    public String test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        return "校验结果为：" + TokenUtil.verify(token);
    }
}