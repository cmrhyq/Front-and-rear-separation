package com.alan.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className Mapper.java
 * @project demoProject
 * @package com.alan.demo.mapper
 * @date 2021/8/21-21:00
 * @email cmrhyq@163.com
 */
@Mapper
public interface UserMapper {

    List<Map<String, Object>> selectAll();

}
