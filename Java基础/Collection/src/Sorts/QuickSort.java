package Sorts;

import java.util.Arrays;

import static Sorts.DataStructure.swap.swap;

@SuppressWarnings({"all"})
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {1, 0, 9, 2, 8, 3, 7, 4, 6, 5, 13, 11, 12};
        quickSort(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }
    public static void quickSort(int[] nums) {
        if(nums==null||nums.length<2) return;
        quickSort(nums,0,nums.length-1);
    }
    public static void quickSort(int[] nums, int l, int r) {
        if (l<r) {
        swap(nums,l+(int) (Math.random()*(r-l+1)),r);
        int[] p = partititons(nums,l,r);
        quickSort(nums,l,p[0]-1);
        quickSort(nums, p[1] +1,r);
        }

    }
    public static int[] partititons(int[] nums, int l, int r) {
        int less=l-1;
        int more = r;
        while (l<more) {
            if (nums[l]<nums[r]) {
                swap(nums, ++less, l++);
            }
            else if (nums[l]>nums[r]) {
                swap(nums,--more,l);
            } else {
                l++;
            }
        }
        swap(nums,more,r);
        return new int[]{less + 1, more};
    }
}
