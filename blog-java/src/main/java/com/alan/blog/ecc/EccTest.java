package com.alan.blog.ecc;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>ecc加密测试方法</p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className EccTest.java
 * @project blog
 * @package com.alan.blog.ecc
 * @date 2021/9/30-0:14
 * @email cmrhyq@163.com
 */
@Slf4j
public class EccTest {

    public static void main(String[] args) throws Exception {
        Map<String, String> map = GenerateKey.getGenerateKey();
//        String privateKey = map.get(ECCEnum.PRIVATE_KEY.value());
//        String publicKey = map.get(ECCEnum.PUBLIC_KEY.value());
        String publicKey = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAErZ6pLPpCR2byNyyswI2DAngtn2sF+YcUFwOWeYimaHUZN08cRRbdiDTA6kMAYbRZ/DWdzKvtd6HNesEVP82DHw==";
        String privateKey = "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQg85/NuQV2wAItuLJhb/VedxuNyZnF92F5Y5l073dUWhegCgYIKoZIzj0DAQehRANCAAStnqks+kJHZvI3LKzAjYMCeC2fawX5hxQXA5Z5iKZodRk3TxxFFt2INMDqQwBhtFn8NZ3Mq+13oc16wRU/zYMf";

        log.info("私钥：" + privateKey);
        log.info("公钥：" + publicKey);
        String text = "202cb962ac59075b964b07152d234b70";
        byte[] b = EccUtils.encrypt(text.getBytes(StandardCharsets.UTF_8), publicKey);
        String str = BASE64Encoder.encodeBuffer(b);
        log.info("密文：" + str);
        String outputStr = new String(EccUtils.decrypt(b, privateKey));
        log.info("原始文本：" + text);
        log.info("解密文本：" + outputStr);
        // 断言
        Assert.assertEquals(outputStr, text, "原始文本与解密文本不相同!\n");
    }

}
