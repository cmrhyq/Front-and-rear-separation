package com.alan.blog.ecc;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NullCipher;
import java.io.Serializable;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


/**
 * <p>ECC加密</p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className EccUtils.java
 * @project blog
 * @package com.alan.blog.utils
 * @date 2021/9/29-23:43
 * @email cmrhyq@163.com
 */
@Slf4j
public class EccUtils implements Serializable {

    /**
     * 加密
     * @param data 加密文本
     * @param publicKey public key
     * @return 密文
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String publicKey) throws Exception{
        byte[] keyBytes = BASE64Decoder.decodeBuffer(publicKey);

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ECCEnum.ALGORITHM.value());

        ECPublicKey publicKey1 = (ECPublicKey) keyFactory.generatePublic(x509KeySpec);

        Cipher cipher = new NullCipher();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey1);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * @param data 加密后的文本
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = BASE64Decoder.decodeBuffer(privateKey);

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ECCEnum.ALGORITHM.value());

        ECPrivateKey priKey = (ECPrivateKey) keyFactory
                .generatePrivate(pkcs8KeySpec);

        Cipher cipher = new NullCipher();
        cipher.init(Cipher.DECRYPT_MODE, priKey);

        return cipher.doFinal(data);
    }
}
