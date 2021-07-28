package org.javaTry.ioc;

import org.javaTry.ioc.javaconfig.SayHello;
import org.javaTry.ioc.javaconfig.javaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(javaConfig.class);
        SayHello sayHello = ctx.getBean("sayHello", SayHello.class);
        System.out.println( sayHello.sayHello("wenlei"));
    }
}
