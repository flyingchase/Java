package Set;

import java.util.HashSet;

@SuppressWarnings({"all"})
public class SetMethods {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        System.out.println(set.add("wang"));
        System.out.println(set.add("wen"));
        System.out.println(set.add("lei"));
        System.out.println(set.add("wo"));
        System.out.println(set.add("ai"));

        set.remove("wo");
        System.out.println("set = " + set);

        set = new HashSet();
        set.add("l");
        set.add("l");
        set.add(new Dog("lele"));
        set.add(new Dog("lele"));
        System.out.println(set);

        set.add(new String("wlzhou"));
        set.add(new String("wlzhou"));

        System.out.println("set = " + set);
    }
}

class Dog{
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}