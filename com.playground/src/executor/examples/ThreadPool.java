package executor.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Work implements Runnable {
    private int id;
    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id: " + id + " is in work - thread id: " + Thread.currentThread().getId());
        long duration = (long) (Math.random()*5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            // interrupts the given thread
            Thread.currentThread().interrupt();
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        // it is a single thread that executes sequentially (for demo purposes only)
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // executes 100 workers
        for (int i = 0; i < 100; i++)
            executorService.execute(new Work(i+1));

        // Initiates an orderly shutdown in which previously submitted tasks are executed,
        // but no new tasks will be accepted.
        // prevents the executor to execute any further task
        executorService.shutdown();

        // terminates actual (running) task
        try {
            // Blocks until all tasks have completed execution after a shutdown request,
            // or the timeout occurs, or the current thread is interrupted, whichever happens first.
            // Returns:
            // true if this executor terminated and false if the timeout elapsed before termination
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                // may cause interrupted excepgtion
                // uncommenting this will not complete all the 100 tasks
//                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
