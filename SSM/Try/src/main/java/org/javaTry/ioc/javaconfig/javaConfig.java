package org.javaTry.ioc.javaconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// 表示是一个Java配置类
@Configuration
@ComponentScan(basePackages = "org.javaTry.ioc")
public class javaConfig {
    @Bean("sh")
    SayHello sayHello() {
        return new SayHello();
    }
}
