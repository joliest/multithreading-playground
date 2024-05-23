package forkandjoinframework.sequentialmaxfinding;


import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class MaxFindingApp {
    public static void main(String[] args) {
        long[] nums = createNumbers(300000000);
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(numOfThreads);

        SequentialMaxFinding sequential = new SequentialMaxFinding();

        long start = System.currentTimeMillis();
        System.out.println("Sequential Max: " + sequential.max(nums));
        System.out.println("Sequential Time: " + (System.currentTimeMillis() - start));


        ParallelMaxFinding parallel = new ParallelMaxFinding(nums, 0, nums.length);
        start = System.currentTimeMillis();
        System.out.println("Parallel Max: " + pool.invoke(parallel));
        System.out.println("Parallel Time: " + (System.currentTimeMillis() - start));
    }

    private static long[] createNumbers(int n) {
        Random random = new Random();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(1000);
        }
        return nums;
    }
}
