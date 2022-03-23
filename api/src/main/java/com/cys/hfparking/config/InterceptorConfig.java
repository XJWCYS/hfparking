package com.cys.hfparking.config;

import com.cys.hfparking.interceptor.CheckTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkTokenInterceptor)
                .addPathPatterns("/user/**")
                .addPathPatterns("/notice/**")
                .addPathPatterns("/owners/**")
                .addPathPatterns("/car/**")
                .addPathPatterns("/butler/**")
                .addPathPatterns("/suggestion/**")
                .addPathPatterns("/outsiders/**")
                .addPathPatterns("/userLoginHistory/all")
                .excludePathPatterns("/owners/excel")
                .excludePathPatterns("/owners/excel1")
                .excludePathPatterns("/butler/excel")
                .excludePathPatterns("/car/excel")
                .excludePathPatterns("/car/excel1")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/excel")
                .excludePathPatterns("/user/excel1")
                .excludePathPatterns("/user/regist");
    }
}
