package structuredconcurrency;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class StructuredConcurrencyBasic {
    public static void main(String[] args) throws InterruptedException {
        // we don't pool virtual threads, we create new one for every task
        // we dispose them after they finished
        try (var scope = new StructuredTaskScope<String>()) {
            var process1 = new LongProcess(1, "result 1");
            var process2 = new LongProcess(3, "result 2");

            // submit the tasks in parallel
            Subtask<String> res1 = scope.fork(process1); // 1 sec
            Subtask<String> res2 = scope.fork(process2); // 3 sec


            // blocking a thread is no longer a problem in Virtual Threads
            // no need to worry about blocking threads
            scope.join(); // waits for the thread to finish execution


            // this will come handy, advatage
            if (res1.state() == Subtask.State.SUCCESS) {
                System.out.println(res1.get());
            }

            if (res2.state() == Subtask.State.SUCCESS) {
                System.out.println(res2.get());
            }

            // combine the results
            // Approach 1: waits for every threads to finish execution before shutdown: https://docs.google.com/document/d/11480hHT4w7OmmUVdmHLgtZmQuMmaQajfWFnentUfbKA/edit#heading=h.afqz8qlcs7mj
            // get() will not block because join() waits for threads to finish
            System.out.println(res1.get() + " " + res2.get());

            // it will shutdown the scope after all child threads terminate
        }
    }
}
