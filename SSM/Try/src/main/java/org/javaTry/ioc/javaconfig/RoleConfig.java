package org.javaTry.ioc.javaconfig;

import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.Comparator;

@ComponentScan(basePackages = {"org/javaTry/ioc"})
public class RoleConfig {
    public static void main(String[] args) {
/*
        ArrayList listTest = new ArrayList();

        listTest.removeIf(e -> e == null);

        String[] strings = new String[10];
        Arrays.sort(strings,String::compareToIgnoreCase);
        System.out::println;
        x-> System.out.println(x);*/

        repeat(10, () -> System.out.println("x"));

    }

    private static void repeat(int i, Runnable action) {
        for (int j = 0; j < i; j++) {
            action.run();
        }
    }
}


