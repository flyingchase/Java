package org.javaTry.ioc.javaconfig;

import org.javaTry.ioc.javaconfig.UserInterface.RoleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Role;

public class RoleConfigtest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RoleConfig.class);

        Role b1 = ctx.getBean(Role.class);
        RoleService b2 = ctx.getBean(RoleService.class);
        b2.printRoleInfo(b1);
    }
}
