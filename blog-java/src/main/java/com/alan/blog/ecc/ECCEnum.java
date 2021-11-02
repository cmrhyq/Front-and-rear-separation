package com.alan.blog.ecc;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className ECCEnum.java
 * @project blog
 * @package com.alan.blog.ecc
 * @date 2021/9/30-0:06
 * @email cmrhyq@163.com
 */
public enum ECCEnum {
    ALGORITHM("EC"),
    PROVIDER("BC"),
    PUBLIC_KEY("PUBLIC_KEY"),
    PRIVATE_KEY("PRIVATE_KEY");


    private String value;

    ECCEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
