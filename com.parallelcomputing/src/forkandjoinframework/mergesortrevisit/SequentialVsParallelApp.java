package forkandjoinframework.mergesortrevisit;

import mergesort.MergeSort;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class SequentialVsParallelApp {
    public static void main(String[] args) {
        int[] nums = createNumbers(100000000);
        SequentialMergeSort sequentialMergeSort = new SequentialMergeSort(nums);

        long start = System.currentTimeMillis();
        sequentialMergeSort.mergeSort(nums);
        System.out.println("Sequential Time: " + (System.currentTimeMillis() - start) + "ms");

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(nums);
        start = System.currentTimeMillis();
        pool.invoke(parallelMergeSort);
        System.out.println("Parallel Time: " + (System.currentTimeMillis() - start) + "ms");

    }

    private static int[] createNumbers(int n) {
        Random random = new Random();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(10000);
        }
        return nums;
    }
}
