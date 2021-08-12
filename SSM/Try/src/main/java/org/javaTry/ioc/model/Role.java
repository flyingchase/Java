package org.javaTry.ioc.model;

import java.lang.annotation.Annotation;

public class Role implements org.springframework.context.annotation.Role {
    private Long id;
    private int age;
    private String note;
    private String name;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", age=" + age +
                ", note='" + note + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int value() {
        return 0;
    }
}
