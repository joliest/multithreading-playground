package parallelization;

import java.util.stream.IntStream;

public class ParallelizationPrimeNumExampleApp {

    public static void main(String[] args) {
        // sequential
        long start = System.currentTimeMillis();
        long numOfPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100)
                        .filter(ParallelizationPrimeNumExampleApp::isPrime)
                        .count();
        System.out.println("Number of primes: " + numOfPrimes);
        System.out.println("Time taken sequential: " + (System.currentTimeMillis() - start));

        // parallel
        start = System.currentTimeMillis();
        numOfPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100)
                .parallel()
                .filter(ParallelizationPrimeNumExampleApp::isPrime)
                .count();
        System.out.println("Number of primes: " + numOfPrimes);
        System.out.println("Time taken parallel: " + (System.currentTimeMillis() - start));
    }

    public static boolean isPrime (long num) {
        // number smaller or equal to 1 are not prime number
        if (num <= 1) return false;
        if (num == 2) return true;

        // even num are not prime numbers
        if (num%2 == 0) return false;

        // check the number in the range [N, sqrt(N)]
        long maxDivisor = (long) Math.sqrt(num);

        // i+=2 - only add numbers
        for (int i = 3; i <= maxDivisor; i+=2) {
            if (num%i == 0) return false;
        }

        return true;
    }
}
