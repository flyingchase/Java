package org.javaTry.ioc;

import org.javaTry.ioc.javaconfig.SayHello;
import org.javaTry.ioc.javaconfig.javaConfig;
import org.javaTry.ioc.javaconfig.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class JavaConfigTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(javaConfig.class);
        SayHello sayHello = ctx.getBean("sh", SayHello.class);
        System.out.println( sayHello.sayHello("flyingchase"));

        UserService allUsers = ctx.getBean(UserService.class);
        List<String> allUsesName = allUsers.getAllUsers();

        System.out.println("allUserName = " + allUsesName);


    }
}
