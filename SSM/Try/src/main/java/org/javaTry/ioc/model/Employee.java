package org.javaTry.ioc.model;

public class Empoyee {
    private String name;
    private Integer age;
    private double sores;

    public Empoyee(String name, Integer age, double sores) {

        this.name = name;
        this.age = age;
        this.sores = sores;
    }

    @Override
    public String toString() {
        return "Empoyee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sores=" + sores +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getSores() {
        return sores;
    }

    public void setSores(double sores) {
        this.sores = sores;
    }
}
