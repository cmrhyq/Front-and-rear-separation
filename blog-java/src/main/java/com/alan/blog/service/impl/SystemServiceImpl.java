package com.alan.blog.service.impl;

import com.alan.blog.ecc.ECCEnum;
import com.alan.blog.ecc.GenerateKey;
import com.alan.blog.entity.SystemKey;
import com.alan.blog.mapper.BlogMapper;
import com.alan.blog.service.SystemService;
import com.alan.blog.utils.FileGenerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className SystemServiceImpl.java
 * @project blog
 * @package com.alan.blog.service.impl
 * @date 2021/9/30-16:47
 * @email cmrhyq@163.com
 */
@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private BlogMapper blogMapper;

    /**
     * 查询系统密钥对
     *
     * @return 密钥对
     */
    @Override
    public SystemKey querySystemKey() {
        return blogMapper.querySystemKey();
    }

    /**
     * 更新公钥的方法
     *
     * @throws NoSuchAlgorithmException -
     * @throws NoSuchProviderException  -
     */
    @Override
    public void updateSystemKey() throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
        Map<String, String> map = GenerateKey.getGenerateKey();
        String publicKey = map.get(ECCEnum.PUBLIC_KEY.value());
        log.info("--获取密钥对成功，公钥为:" + publicKey);
        log.info("--正在更新公钥和文件");
        try {
            FileGenerate.delFile("SystemKey");
            FileGenerate.createFile("SystemKey", map.get(ECCEnum.PUBLIC_KEY.value()) + "\r\n" + map.get(ECCEnum.PRIVATE_KEY.value()));
            log.info("--更新完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            blogMapper.updateSystemKey(map);
        }
    }
}
