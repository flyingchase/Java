package Map;

import java.util.HashMap;
import java.util.Objects;

@SuppressWarnings({"all"})

public class HashMapDetail {

    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap.put(1,"One");
        hashMap.put(2,"Two");
        hashMap.put(3,"Three");
        hashMap.put(4,"Four");
        hashMap.put(5,"Seven");
        hashMap.put(6,"Six");
        hashMap.put("Seven",'z');
        hashMap.put('7','z');
        hashMap.put(7,'z');
        hashMap.put("8",'z');
        hashMap.put(null,"z");
//        hashMap.put(null,null);
        System.out.println("treeMap = " + hashMap);
        System.out.println("treeMap.size() = " + hashMap.size());
    }
}



class A{
    private int i;

    public A(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof A)) return false;
        A a = (A) o;
        return i == a.i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i);
    }
}


