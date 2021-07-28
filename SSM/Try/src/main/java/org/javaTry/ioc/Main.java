package org.javaTry.ioc;

import org.javaTry.ioc.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        FileSystemXmlApplicationContext f1 = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");

//        o3(ctx);

        User user4 = ctx.getBean("user4", User.class);
        System.out.println("user4 = " + user4);
    }

    private static void o3(ClassPathXmlApplicationContext ctx) {
        User u3 = ctx.getBean("user3", User.class);
        System.out.println(u3);
    }

    private static void otherBackup01(ClassPathXmlApplicationContext ctx) {
        User u1 = ctx.getBean("user", User.class);
        User u2 = (User) ctx.getBean("user");
        User u3 = ctx.getBean(User.class);

        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
    }

    private static void otherBackup01(FileSystemXmlApplicationContext f1) {
        User user = f1.getBean("user", User.class);
        System.out.println(user);
    }

}


