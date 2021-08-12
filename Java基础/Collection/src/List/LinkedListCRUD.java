package List;


import java.util.LinkedList;
@SuppressWarnings({"all"})
public class LinkedListCRUD {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 2; i++) {
            linkedList.add(i);
        }
        linkedList.add(100);
        linkedList.add(200);
        for (Object o:
             linkedList) {
            System.out.println(o);
        }

        linkedList.set(0,"wlzhou");
        linkedList.remove(1);
        for (Object o:
             linkedList) {
            System.out.println(o);
        }
        Object obj = linkedList.get(0);
        System.out.println("obj = " + obj);
        System.out.println("linkedList.getFirst() = " + linkedList.getFirst());
        System.out.println("linkedList.getLast() = " + linkedList.getLast());

    }
}
