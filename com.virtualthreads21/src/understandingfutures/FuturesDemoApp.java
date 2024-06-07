package understandingfutures;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FuturesDemoApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> result = executorService.submit(new FutureTask());

        while (!result.isDone()) {
            System.out.println("Main thread is waiting for the result");
        }

        // ❗️ ❗️ ❗️ blocks the main thread. → Disadvantage of Future
        // It waits for future task to finish execution
        String res = result.get();
        System.out.println(res);
    }
}
