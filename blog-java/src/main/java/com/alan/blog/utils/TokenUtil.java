package com.alan.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * <p>Token的生成和验证方法</p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className TokenUtil.java
 * @project blog
 * @package com.alan.blog.utils
 * @date 2021/9/18-16:38
 * @email cmrhyq@163.com
 */
@Slf4j
public class TokenUtil {

    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 60 * 60 * 1000;
    /**
     * 密钥
     */
    private static final String TOKEN_SECRET = "cqyh";

    /**
     * 签名生成
     *
     * @param userPhone 手机号
     * @return String - token
     */
    public static String sing(String userPhone) {
        String token = "";
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auto0")
                    .withClaim("userPhone", userPhone)
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     *
     * @param token token
     * @return boolean - true or false
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auto0").build();
            DecodedJWT jwt = verifier.verify(token);
            log.info("认证账户:" + jwt.getClaim("userPhone").asString());
            log.info("认证结果:通过");
            log.info("过期时间:" + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
