package understandingfutures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureDemoApp {
    public static void main(String[] args) {
        ExecutorService cpuExecutor = Executors.newFixedThreadPool(5);
        ExecutorService ioExecutor = Executors.newCachedThreadPool(); // threads will be reused under the hood

        CompletableFuture.supplyAsync(() -> "Hello World!!!", cpuExecutor)// // cpuExecutor is optional, but we can pass it
                // thenApplyAsync() ↓ ↓ ↓
                // given thread will execute first operation,
                // another thread will start and execute this
                .thenApplyAsync(string -> string.toUpperCase(), ioExecutor) // ioExecutor is optional, but we can pass it
                .thenApply(string -> string + " something!!!")
                // v thenAccept does not return anything
                .thenAccept(System.out::println);

    }
}
