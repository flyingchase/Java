package org.javaTry.ioc.javaconfig.UserInterface;

import org.javaTry.ioc.model.Role;

public interface RoleService {
    public void printRoleInfo(Role role);

    void printRoleInfo(org.springframework.context.annotation.Role b1);

}
