package sumproblem;

import java.util.Random;

public class ParallelVsSequential {
    public static void main(String[] args) {
        Random random = new Random();
        int[] nums = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            nums[i] = random.nextInt(100);
        }
        int numOfThreads = Runtime.getRuntime().availableProcessors();

        // sequential
        SequentialSum sequentialSum = new SequentialSum();
        long start = System.currentTimeMillis();
        System.out.println("Sequential sum: " + sequentialSum.sum(nums));
        System.out.println("Sequential time: " + (System.currentTimeMillis() - start));

        // parallel
        ParallelSum parallelSum = new ParallelSum(numOfThreads);
        start = System.currentTimeMillis();
        System.out.println("Parallel sum: " + parallelSum.sum(nums));
        System.out.println("Parallel time: " + (System.currentTimeMillis() - start));
    }
}