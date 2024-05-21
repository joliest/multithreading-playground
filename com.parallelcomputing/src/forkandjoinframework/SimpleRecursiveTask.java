package forkandjoinframework;

import java.util.concurrent.RecursiveTask;

// we are returning Integer
public class SimpleRecursiveTask extends RecursiveTask<Double> {
    private double num;

    public SimpleRecursiveTask(double num) {
        this.num = num;
    }

    @Override
    protected Double compute() {
        if (num > 100) {
            // paralleziation if numbers are too many
            System.out.println("Parallel execution, so split the task: " + num);
            SimpleRecursiveTask task1 = new SimpleRecursiveTask(num/2);
            SimpleRecursiveTask task2 = new SimpleRecursiveTask(num/2);

            // we add the tasks to the thread pool (parallel)
            task1.fork();
            task2.fork();

            // wait for these tasks to finisj
            double subSolution = 0;
            // join() waits for the operation to return the value
            subSolution += task1.join();
            subSolution += task2.join();

            return subSolution;
        } else {
            System.out.println("Sequential execution:" + num);
            // problem is too small, we can use sequential approach
            return 2*num;
        }
    }
}
