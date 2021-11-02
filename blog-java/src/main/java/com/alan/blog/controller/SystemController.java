package com.alan.blog.controller;

import com.alan.blog.entity.SystemKey;
import com.alan.blog.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className SystemController.java
 * @project blog
 * @package com.alan.blog.controller
 * @date 2021/9/30-16:50
 * @email cmrhyq@163.com
 */
@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @PostMapping("/queryKey")
    public SystemKey queryKey(){
        return systemService.querySystemKey();
    }

    @PostMapping("/updateKey")
    public void updateKey() throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        systemService.updateSystemKey();
    }
}
