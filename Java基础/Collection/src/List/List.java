/*
 * @Author: gunjianpan
 * @Date:   2021-05-13 10:32:23
 * @Last Modified by:   gunjianpan
 * @Last Modified time: 2021-05-13 10:55:49
 */

package List;

import java.util.ArrayList;
import java.util.Arrays;

public class List {

    private static String a;
    private static int anInt;
    private static int anInt1;

    public static void main(String[] args) {

        ArrayList list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("1");
        System.out.println(list);
//        list.clear();
//        System.out.println(list);
        ArrayList list2 = new ArrayList();
        list2.add("5");
        list2.add("6");
        list2.add("7");
        list2.add("8");
        list.addAll(list2);
        System.out.println(list);
//        System.out.println(list.remove(3));
//        System.out.println(list);
        list.set(3, "4");
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.subList(0, 1));
        list.add(0, "0");
        System.out.println(list.size());


        System.out.println(list);
        System.out.println("list2 = " + list2);

        for (int i = 0; i < 12; i++) {

        }
//        return list;

//        {'1','2','3'}.f

//        {1,2,3}.re

//        String stri = "abc";
//        List<Object> list01 = new ArrayList<>();
//
    }
}

