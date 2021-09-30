package com.alan.blog.entity;

import lombok.Data;

/**
 * <p>用户信息实体类</p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className SystemKey.java
 * @project blog
 * @package com.alan.blog.entity
 * @date 2021/9/30-15:39
 * @email cmrhyq@163.com
 */
@Data
public class SystemKey {

    private String publicKey;
    private String privateKey;
}
