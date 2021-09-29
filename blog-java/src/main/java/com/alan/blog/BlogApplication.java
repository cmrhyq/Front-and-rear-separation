package com.alan.blog;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Alan Huang
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.alan.blog.mapper")
public class BlogApplication {

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        SpringApplication.run(BlogApplication.class, args);
        long endTime = System.currentTimeMillis();
        log.info("程序启动成功！启动耗时：" + (endTime - beginTime) + "ms");
    }

}
