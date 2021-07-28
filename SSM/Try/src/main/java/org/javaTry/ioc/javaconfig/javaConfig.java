package org.javaTry.ioc.javaconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 表示是一个Java配置类
@Configuration
public class javaConfig {
    @Bean
    SayHello sayHello() {
        return new SayHello();
    }
}
