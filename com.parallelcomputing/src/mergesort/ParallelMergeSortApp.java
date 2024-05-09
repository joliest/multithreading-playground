package mergesort;

import java.util.Random;

public class ParallelMergeSortApp {
    public static void main(String[] args) {
        // number of processors avail for JVM
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        int[] number1 = createArray(10000000);
        int[] number2 = new int[number1.length];

        // copying number1
        for (int i = 0; i < number1.length; i++) {
            number2[i] = number1[i];
        }

        // PARALLEL MERGE SORT
        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(number1);
        long startTime1 = System.currentTimeMillis();
        parallelMergeSort.parallelMergeSort(0, number1.length - 1, numOfThreads);
        long endTime1 = System.currentTimeMillis();
        System.out.printf("Parallel time: %6d ms\n", endTime1 - startTime1);

        // SEQUENTIAL MERGE SORT
        startTime1 = System.currentTimeMillis();
        MergeSort sequentialMergeSort = new MergeSort(number2);
        sequentialMergeSort.sort();
        endTime1 = System.currentTimeMillis();
        System.out.printf("Sequential time: %6d ms\n", endTime1 - startTime1);

    }

    private static int[] createArray(int n) {
        Random random = new Random();
        int[] a = new int[n];

        for( int i = 0; i < n; i++) {
            a[i] = random.nextInt(n);
        }

        return a;
    }
}
