package Sorts;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] nums={1,0,9,2,3,8,7,4,5,12,11,6};
        heapsort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void heapsort(int[] nums) {
        if (nums==null||nums.length<2) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            heapInsert(nums,i);
        }
        int size = nums.length;
        while (size>0) {
            swap(nums,0,--size);
            heapIfy(nums,0,size);
        }
    }

    public  static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
    public static void heapInsert(int[] nums, int index) {
        while (nums[index]>nums[(index-1)/2]) {
            swap(nums,index,(index-1)/2);
            index=(index-1)/2;
        }
    }

    public static void heapIfy(int[] nums, int index, int size) {
        int left = 2*index+1;
        while (left<size) {
            int largest = (left+1<size) && (nums[left+1]>nums[left])?left+1:left;
            largest = nums[largest]>nums[index]?largest:index;
            if(largest==index) return;
            swap(nums,index,largest);
            index=largest;
            left=2*index+1;
        }
    }

}
