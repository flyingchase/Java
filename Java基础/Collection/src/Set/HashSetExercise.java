package Set;

import java.util.HashSet;
import java.util.Objects;

@SuppressWarnings({"all"})

public class HashSetExercise {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(new EmployeeNext("pan1",28));
        hashSet.add(new EmployeeNext("pan2",29));
        hashSet.add(new EmployeeNext("pan3",18));
        hashSet.add(new EmployeeNext("pan4",22));
        hashSet.add(new EmployeeNext("pan5",24));
        hashSet.add(new EmployeeNext("pan4",22));
        System.out.println("hashSet = " + hashSet);
        System.out.println("hashSet.size() = " + hashSet.size());
        String zhou = new String("zhou");
        zhou = "zheshismgui";
//        zhou = "zheshismgui";

    }
}



class MyDate{
    private int year;
    private int month;
    private int day;
}
class EmployeeNext {
    private String name;
//    private String name;
    private int age;



    public EmployeeNext(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';

    }

    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeNext)) return false;
        EmployeeNext employee = (EmployeeNext) o;
        return age == employee.age && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}