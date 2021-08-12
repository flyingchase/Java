package List;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"all"})
public class ListExercide02 {
    public static void main(String[] args) {
        List arrayList = new LinkedList();
        arrayList.add(new Books("hong","cao",100));
        arrayList.add(new Books("xi","wu",10));
        arrayList.add(new Books("shui","shi",9));
        arrayList.add(new Books("san","luo",80));

//        Iterator iterator = arrayList.iterator();
//        while (iterator.hasNext()) {
//            Object next =  iterator.next();
//            System.out.println("next = " + next);
//        }
//
//        for (int i = 0; i < arrayList.size(); i++) {
//            System.out.println(arrayList.get(i));
//        }
//
//        for (int i = 0; i < ; i++) {
//
////        }
//        for (Object o:
//             arrayList) {
//            System.out.println("o = " + o);
//        }

//        arrayList.sort();


        sort(arrayList);
        System.out.println("arrayList = " + arrayList);

        System.out.println(arrayList.getClass());

    }
        public static void sort(List aList) {
            for (int i = 0; i < aList.size()-1; i++) {
                for (int j = 0; j < aList.size()-1-i; j++) {
                    Books books =(Books) aList.get(j);
                    Books books1 =(Books) aList.get(j+1);
                    if(books.getPrice()>books1.getPrice()) {
                        aList.set(j,books1);
                        aList.set(j+1,books);
                    }
                }
            }
        }
}
