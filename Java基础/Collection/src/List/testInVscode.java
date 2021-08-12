package List;

import java.util.LinkedList;

/*
 * @Author: gunjianpan
 * @Date:   2021-05-14 21:27:08
 * @Last Modified by:   gunjianpan
 * @Last Modified time: 2021-05-14 21:31:19
 */

/**
 * testInVscode
 */
public class testInVscode {

    @SuppressWarnings({"all"})
    
    public static void main(String[] args) {
    
        var alist = new LinkedList();
        System.out.println(alist.getClass());
        for (int i = 0; i < 3; i++) {
            alist.add(i);
        }
        alist.add("wenlei");
        alist.remove(1);
        alist.set(0, "woai");
        System.out.println(alist.toString());
    }

}