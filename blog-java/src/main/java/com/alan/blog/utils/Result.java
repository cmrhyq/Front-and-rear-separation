package com.alan.blog.utils;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private List<?> data;
    private String msg;
    private Integer status;

}
