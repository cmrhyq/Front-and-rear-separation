package com.alan.blog.config;

import com.alan.blog.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * <p>拦截器设置</p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className WebConfiguration.java
 * @project blog
 * @package com.alan.blog.config
 * @date 2021/9/18-17:10
 * @email cmrhyq@163.com
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    /**
     * 构造方法
     *
     * @param tokenInterceptor
     */
    public WebConfiguration(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    /**
     * 添加Cors映射
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    /**
     * 配置异步支持
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    /**
     * 增加一个拦截器 设置拦截规则
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/user/register/");
        excludePath.add("/user/login/");
        excludePath.add("/static/**/");
        excludePath.add("/assets/**/");

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
