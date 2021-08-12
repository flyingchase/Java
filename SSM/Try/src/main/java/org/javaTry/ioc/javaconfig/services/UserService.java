package org.javaTry.ioc.javaconfig.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public List<String> getAllUsers() {

        List<String> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add("woai "+ i);
        }
        return users;
    }
}
