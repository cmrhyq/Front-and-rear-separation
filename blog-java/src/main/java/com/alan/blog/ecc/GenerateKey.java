package com.alan.blog.ecc;

import java.io.Serializable;

import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className GenerateKdy.java
 * @project blog
 * @package com.alan.blog.ecc
 * @date 2021/9/30-0:10
 * @email cmrhyq@163.com
 */
public class GenerateKey implements Serializable {

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public static Map<String,String> getGenerateKey() throws NoSuchProviderException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ECCEnum.ALGORITHM.value(),
                ECCEnum.PROVIDER.value());
        keyPairGenerator.initialize(256, new SecureRandom());
        KeyPair kp = keyPairGenerator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) kp.getPublic();
        ECPrivateKey privateKey = (ECPrivateKey) kp.getPrivate();
        Map<String,String> map = new HashMap<>();

        map.put(ECCEnum.PRIVATE_KEY.value(), BASE64Encoder.encodeBuffer(privateKey.getEncoded()));
        map.put(ECCEnum.PUBLIC_KEY.value(), BASE64Encoder.encodeBuffer(publicKey.getEncoded()));
        return map;
    }
}
