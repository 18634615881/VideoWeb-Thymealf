package com.example.demo.intercept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/image/**").addResourceLocations("file:D:\\new\\image\\");//windows下的映射
                registry.addResourceHandler("/video/**").addResourceLocations("file:D:\\new\\video\\");
              //registry.addResourceHandler("/image/**").addResourceLocations("file:/soft/jar/static/image/");//Linux下的路径映射
              //registry.addResourceHandler("/video/**").addResourceLocations("file:/soft/jar/static/video/");

            }
        };
    }
}
