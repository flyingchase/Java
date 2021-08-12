package Set;

@SuppressWarnings({"all"})
public class HashSetStructure {
    public static void main(String[] args) {
//        LinkedHashSet
    }
        // 数组+链表模拟HashSet/HashMap structure
        // 数组类型为 Node
       /* org.w3c.dom.Node[] tables = new org.w3c.dom.Node[16];
        System.out.println(tables);

        org.w3c.dom.Node wo = new org.w3c.dom.Node("ai", null);
        tables[2]=wo;
        org.w3c.dom.Node wenlei =   new Node("wlei", null);
//        tables[3]=wenlei;
        wo.next=wenlei;

        Node zhou = new Node("song", null);
        wenlei.next=zhou;

        Node wang = new Node("xu", null);
        tables[3]=wang;

        System.out.println("tables = " + tables);


    }*/
}
class Node{
    // Node point to Node--->linklist
    Object item;
    Node next;

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }
}
