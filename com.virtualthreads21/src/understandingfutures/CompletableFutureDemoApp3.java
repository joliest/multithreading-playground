package understandingfutures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class CompletableFutureDemoApp3 {
    public static void main(String[] args) {

        // try with resource approach
        try (var service = Executors.newVirtualThreadPerTaskExecutor()) {
            CompletableFuture.supplyAsync(DbQuery::run, service)
                    .thenCombine(CompletableFuture
                            .supplyAsync(RestQuery::run, service),
                            (res1, res2) -> {
                                String toReturn = res1 + " " + res2;
                                return toReturn;
                            })
                    .thenAccept(System.out::println);
//                  .join(); // instead of thenAccept
//                    join() was discouraged before because it blocks the thread

            // try with resources closes (shuts down the threads) automatically
            // the threads will be executed (we will wait for them to finish)
        }
    }
}
