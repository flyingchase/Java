package org.javaTry.ioc.javaconfig.services;

import org.javaTry.ioc.javaconfig.UserInterface.RoleService;
import org.javaTry.ioc.model.Role;

public abstract class RoleServiceImplements implements RoleService {

    @Override
    public void printRoleInfo(Role role) {
        System.out.println(role.getId());
        System.out.println(role.getAge());
        System.out.println(role.getNote());
        System.out.println(role.getName());
    }
}
