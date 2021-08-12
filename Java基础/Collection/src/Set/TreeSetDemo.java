package Set;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o2).compareTo(((String) o1));
            }
        });

//        TreeSet treeSet = new TreeSet();
        treeSet.add("jack");
//        treeSet.add("jack");
        treeSet.add("tom");
        treeSet.add("sp");
        treeSet.add("ssp");

        System.out.println(treeSet);

    }
}
