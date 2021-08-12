package org.javaTry.ioc;

import java.util.Arrays;

public class MinHeap {
    public static void main(String[] args) {
        int[] nums = {11, 0, 9, 2, 6, 3, 8, 4, 7, 5, 16, 1, 14, 12, 13, 15};
        int[] heapsort = heapsort(nums);
        System.out.println(Arrays.toString(heapsort));
    }

    private static int[] heapsort(int[] arrs) {
        if (arrs == null || arrs.length < 2) {
            return arrs;
        }

        int[] nums = Arrays.copyOf(arrs, arrs.length);
        for (int i = 0; i < nums.length; i++) {
            heapinsert(nums, i);
        }
        int size = nums.length;
        while (size-- > 0) {
            swap(nums, 0, size);
            heapIfy(nums, 0, size);
        }
        return nums;
    }

    private static void heapIfy(int[] nums, int index, int size) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        while (left < size) {
            int min = right<size&&nums[left]>nums[right]?right:left;
            min= nums[min]>nums[index]?index:min;
            if (min==index) {
                break;
            }
            swap(nums,min,index);
            index=min;
            left=2*index+1;
            right=left+1;
        }
    }

    private static void heapinsert(int[] nums, int index) {
        int parent = (index - 1) / 2;
        while (nums[parent] > nums[index]) {
            swap(nums, parent, index);
            index = parent;
            parent = (index - 1) / 2;
        }

    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
