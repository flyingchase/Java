package Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"all"})

public class MapSource {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("1","one");
        map.put("2","one");
        map.put(new car("aodi",10086),"one");
        map.put("4","one");
        map.put("5","one");



        System.out.println("map = " + map);

        Set set = map.entrySet();
        System.out.println("set.getClass() = " + set.getClass());
        for (Object o:set) {
            // object向下转型
            Map.Entry entry = (Map.Entry) o;
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        }
    }
}

class car {
    private String name;
    private double price;

    public car(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
