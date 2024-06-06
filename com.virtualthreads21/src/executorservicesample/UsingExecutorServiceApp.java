package executorservicesample;

import basic.VirtualTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingExecutorServiceApp {
    public static void main(String[] args) {
        // making sure they will be managed in most sufficient way
        // virtual thread will notbe reused as they are lightweight
        // we are not going to use thread pool
        // newVirtualThreadPerTaskExecutor -> virtual threads are daemon threads, we would like to make sure that we wait for the threads to finish execution
        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) { // best to do it with "try with resource approach"
            // whenever we submit the new virtual thread, service will wait for the given thread to finish
            // no need to call join() method
            executorService.submit(VirtualTask::run);
            executorService.submit(VirtualTask::run);
            executorService.submit(VirtualTask::run);
        }
    }
}
