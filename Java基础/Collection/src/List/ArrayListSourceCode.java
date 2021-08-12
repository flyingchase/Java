package List;

import java.util.ArrayList;

@SuppressWarnings({"all"})
public class ArrayListSourceCode {
    public static void main(String[] args) {
        ArrayList list = new ArrayList(5);
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }

        for (int i = 11; i <=15 ; i++) {
            list.add(i);
        }
        list.add(100);
        list.add(200);
        list.add(null);
        for (Object o:
             list) {
            System.out.println(o);
        }

    }
}