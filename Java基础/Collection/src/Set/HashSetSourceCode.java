package Set;

import java.util.HashSet;

@SuppressWarnings({"all"})
public class HashSetSourceCode {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
//        HashMap
      /*  hashSet.add("java");
        hashSet.add("php");
        hashSet.add("golang");
        hashSet.add("rust");
        hashSet.add("c");
        hashSet.add("cpp");
        hashSet.add("python");
        hashSet.add("js");
        hashSet.add("erlang");
        hashSet.add("kolin10");
        for (int i = 0; i < 53; i++) {
            hashSet.add("etc"+i);
        }
        hashSet.add("resizeWith64");
        System.out.println("hashSet.size() = " + hashSet.size());
        System.out.println("hashSet = " + hashSet);*/

        for (int i = 1; i <=12; i++) {
            hashSet.add(new A(i));
        }
        System.out.println("hashSet = " + hashSet);
        System.out.println("hashSet.size() = " + hashSet.size());
    }
    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}

class A {
    private  int n;

    public A(int n) {
        this.n = n;
    }


    @Override
    public String toString() {
        return "A{" +
                "n=" + n +
                '}';
    }

    @Override
    public int hashCode() {
        return 100;
    }
}



