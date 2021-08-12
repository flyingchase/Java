package List;

import java.util.ArrayList;
import java.util.Iterator;

public class ListExercise {
    // ArrayList<>().var

    // new ArrayList<>().var

    // new ArrayList.var
//    ArrayList().va

//    ArrayList()
//    new ArrayList().var
//    new ArrayList().var
//    new ArrayList<>(
//    ArrayList arrayList = new ArrayList();
    
//    arrayList

    public static void main(String[] args) {
        ArrayList aList = new ArrayList();

        for (int i = 0; i < 10; i++) {
            aList.add("hello" + i);
        }

        System.out.println("aList = " + aList);

        aList.add(2, "wenlei");
        System.out.println("aList = " + aList);

        System.out.println("aList.get(5) = " + aList.get(5));
        System.out.println("aList.remove(6) = " + aList.remove(6));
        System.out.println("aList = " + aList);
        System.out.println("aList.size() = " + aList.size());

        aList.set(1, "woai");
        System.out.println(aList);

        Iterator iterator = aList.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println("next = " + next);
        }

//        () -> aList.forEach()

        for (Object o:
             aList) {
            System.out.println(o);
        }

        for (int i = 0; i < aList.size(); i++) {
            Object o = aList.get(i);
        }

//        List vector = new Vector();
//        vector.add

    }

    }







