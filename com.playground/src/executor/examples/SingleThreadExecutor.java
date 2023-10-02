package executor.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutor {
    public static void main(String[] args) {
        // single thread will execute the task sequentially
        // so one after another
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task(i));
        }
    }
}
