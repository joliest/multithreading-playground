package understandingcompleteablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemoApp2 {
    public static void main(String[] args) {

        // combining two completable futures
        CompletableFuture.supplyAsync(() -> "hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> "World"), (s1, s2) -> s1 + " " + s2)
                .thenApply(s -> s.toUpperCase())
                .thenAccept(System.out::println);
    }
}
