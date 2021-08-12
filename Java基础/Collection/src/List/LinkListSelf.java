package List;
@SuppressWarnings({"all"})
public class LinkListSelf {
    static class Node{
        public Object item;
        public Node next;
        public Node pre;
        public Node(Object name) {
            this.item=name;
        }

        @Override
        public String toString() {
            return "Node name = "+item;
        }
    }
    public static void main(String[] args) {
        Node a = new Node("aaa");
        Node b = new Node("bbb");
        Node c = new Node("ccc");

        a.next=b;
        b.next=c;
        c.pre=b;
        b.pre=a;
        Node first = new Node(-1);
        Node last = new Node(-1);
        first=a;
        last=c;
       /* while (true) {
            if(first==null) {
                break;
            }
            System.out.println(first);
            first=first.next;
        }*/
        while (first!=null) {
            System.out.println(first.item);
            first=first.next;
        }
       /* while (first.pre!=null) {
            first=first.pre;
            if (first==null) {

            System.out.println(first.item);
            }
        }*/

        while (last!=null) {
            System.out.println(last.item);
            last=last.pre;
        }

    }
}
