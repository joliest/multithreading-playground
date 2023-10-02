package executor.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class FixedThreadPools {
    public static void main(String[] args) {
        int NUM_OF_THREADS = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task(i));
        }
    }
}
