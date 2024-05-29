package parallelization;

import java.util.stream.LongStream;

public class BasicParallelizationApp {
    public static void main(String[] args) {
        // sequential
        long start = System.currentTimeMillis();
        System.out.println(sum(1000000000));
        System.out.println("Time taken sequential: " + (System.currentTimeMillis() - start));

        // parallel
        start = System.currentTimeMillis();
        System.out.println(parallelSum(1000000000));
        System.out.println("Time taken parallel: " + (System.currentTimeMillis() - start));
    }

    private static long sum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    private static long parallelSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel() // ←  that's it
                .reduce(0L, Long::sum);
    }
}
