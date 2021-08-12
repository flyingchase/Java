package List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

@SuppressWarnings({"all"})
public class ArrayListDetails {

    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        Vector<Object> objects1 = new Vector<>();

        System.out.println(objects.getClass());

        List list = new LinkedList();

        System.out.println(list.getClass());


    }

}

