package Sorts;

import java.util.Arrays;

@SuppressWarnings({"all"})

public class MergeSortExercise0N {
    public static void mergesort(int[] nums) {
        if (nums==null||nums.length<2) {
            return;
        }
        mergesort(nums,0,nums.length - 1);
    }

    public static void mergesort (int[] nums, int l ,int r) {
        if (l>r) return;
        int mid = l + ((r-l)/2);
        mergesort(nums, l ,mid);
        mergesort(nums,mid + 1, r);
        merge(nums,l,mid,r);
    }

    public static void merge(int[] nums, int l, int mid, int r) {
        int p1=l,p2=mid,i=0;
        int[] helper=new int[r-l+1];
        while (p1<=mid&&p2<=r) {
            helper[i++] = nums[p1]<nums[p2]?nums[p1++]:nums[p2++];

        }
        while (p1<=mid) {
            helper[i++] = nums[p1++];
        }
        while (p2<=r) {
            helper[i++] = nums[p2++];
        }

        for (i=0;i<helper.length; i++) {
            nums[l+i] = helper[i];
        }
    }
    public static void main(String[] args) {
        int[] nums=new int[]{1,2,9,8,0,3,7,4,6,5,13,11,12};
        mergesort(nums);
        System.out.println(Arrays.toString(nums));


    }
}
