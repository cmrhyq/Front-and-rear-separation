package com.alan.blog.service;

import com.alan.blog.entity.SystemKey;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className SystemService.java
 * @project blog
 * @package com.alan.blog.service
 * @date 2021/9/30-16:46
 * @email cmrhyq@163.com
 */
public interface SystemService {

    /**
     * 更新公钥的方法
     *
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    void updateSystemKey() throws NoSuchAlgorithmException, NoSuchProviderException, IOException;

    /**
     * 查询系统密钥对
     *
     * @return 密钥对
     */
    SystemKey querySystemKey();
}
