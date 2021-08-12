package Set;

import java.util.LinkedHashSet;
import java.util.Objects;

@SuppressWarnings({"all"})

public class LinkedHashSetDetail {
    public static void main(String[] args) {
//        Set set = new LinkedHashSet();
//        set.add(new String("AA"));
//        set.add(123);
//        set.add(123);
//        set.add(new Customer("liu",10086));
//        set.add(456);
//        set.add("zhouql");
//        System.out.println("set.toString() = " + set.toString());
//        set.remove(456);
//        System.out.println("set.toString() = " + set.toString());

        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new car("aotu",1000));
        linkedHashSet.add(new car("aodi",3));
        linkedHashSet.add(new car("falali",10000000));
        linkedHashSet.add(new car("aodi",3));
        linkedHashSet.add(new car("baoshijie",70000000));
        linkedHashSet.add(new car("aodi",3));


        System.out.println("linkedHashSet = " + linkedHashSet);
    }


}

class Customer{
    public String name;
    public long number;

    public Customer(String name, long number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}'+'\n';
    }
}


class car {
    private  String name;
    private  double price;

    public car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof car)) return false;
        car car = (car) o;
        return Double.compare(car.price, price) == 0 && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}'+'\n';
    }
}