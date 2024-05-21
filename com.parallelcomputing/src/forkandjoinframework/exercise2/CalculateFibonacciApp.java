package forkandjoinframework.exercise2;

import java.util.concurrent.ForkJoinPool;

public class CalculateFibonacciApp {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CalculateFibonacci fibonacci = new CalculateFibonacci(25);
        System.out.println(forkJoinPool.invoke(fibonacci));
    }
}
