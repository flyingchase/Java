package org.javaTry.ioc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonSorts {
    public static void main(String[] args) {
        int[] nums = {11, 0, 9, 2, 6, 3, 8, 4, 7, 5, 16, 1, 14, 12, 13, 15};

        System.out.println(Arrays.toString(heapSort(nums)));


        List<String> waiters = Arrays.asList("djsal", "qywiu", "as", "java", "djk", "jao", "asad", "bdsad", "vasdda", "vasd");

        Stream<String> stream = waiters.stream();
        Stream<String> d = stream.filter(s -> s.startsWith("d"));
        Stream<String> stringStream = d.map(String::toLowerCase);
        Stream<String> sorted = stringStream.sorted();
        List<String> collect = sorted.collect(Collectors.toList());
        System.out.println(collect);


    }

    private static int[] heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        int[] nums = Arrays.copyOf(arr, arr.length);
        int size = nums.length;
        for (int i = 0; i < nums.length; i++) {
            heapInsert(nums, i);
        }
        while (size-- > 0) {
            swap(nums, 0, size);
            heapify(nums, 0, size);
        }
        return nums;
    }

    private static void heapify(int[] nums, int index, int size) {
        int left = 2 * index + 1;
        while (left < size) {
            int largest = left + 1 < size && nums[left + 1] > nums[left] ? left + 1 : left;
            largest = Math.max(index, largest);
            if (index == largest) {
                break;
            }
            swap(nums, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void heapInsert(int[] nums, int index) {
        while (nums[index] > nums[(index - 1) / 2]) {
            swap(nums, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }





}
