package com.alan.blog.utils;

import lombok.Data;

import java.util.List;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className Result.java
 * @project blog
 * @package com.alan.blog.utils
 * @date 2021/9/15-11:35
 * @email cmrhyq@163.com
 */
@Data
public class Result {
    private String code;
    private String msg;
    private Integer status;
    private List<?> list;
    private String token;
    private Object obj;
}
