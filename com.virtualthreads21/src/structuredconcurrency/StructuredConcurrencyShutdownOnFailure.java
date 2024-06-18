package structuredconcurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class StructuredConcurrencyShutdownOnFailure {
    public static void main(String[] args) throws InterruptedException {
        // ShutdownOnFailure will give access to throwIfFailed()
        // it will enable us to stop all threads including running child threads when something went wrong
        // prevents us from having orphan treads
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var process1 = new LongProcessShutdownOnFailure(1, "result 1", true);
            var process2 = new LongProcessShutdownOnFailure(3, "result 2", false);

            Subtask<String> res1 = scope.fork(process1); // 1 sec
            Subtask<String> res2 = scope.fork(process2); // 3 sec


            scope.join(); // waits for the thread to finish execution including child

            try {
                // without this, it w
                scope.throwIfFailed();

                // no need to check if SUCESS or FAIL, because it already throws an error
                // you will get IllegalStateException if you put this outside
                System.out.println(res1.get() + " - " + res2.get());
            } catch (ExecutionException e) {
                System.out.println("TERMINATING ALL THREADS");
            }
        }
    }
}
