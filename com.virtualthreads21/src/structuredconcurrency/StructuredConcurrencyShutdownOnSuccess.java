package structuredconcurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class StructuredConcurrencyShutdownOnSuccess {
    public static void main(String[] args) throws InterruptedException {
        // We will add a type string as we are returning a String
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            var process1 = new LongProcessShutdownOnFailure(1, "result 1", false);
            var process2 = new LongProcessShutdownOnFailure(5, "result 2", false);

            Subtask<String> res1 = scope.fork(process1); // 1 sec
            Subtask<String> res2 = scope.fork(process2); // 5 sec


            scope.join(); // waits for the thread to finish execution including child

            // instead of get(), returns the result of first subtask that completed successfully
            String result = null;
            try {
                result = scope.result();
            } catch (ExecutionException e) {
                System.out.println("Both tasks failed");
            }
            System.out.printf("Result: " + result);
        }
    }
}
