package forkandjoinframework;

import java.util.concurrent.ForkJoinPool;

public class SimpleRecursiveTaskApp {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        // doubles the result by adding
        SimpleRecursiveTask task = new SimpleRecursiveTask(800);
        System.out.println(pool.invoke(task));

        /**
         *
         * OUTPUT:
         *
         * Parallel execution, so split the task: 800.0
         * Parallel execution, so split the task: 400.0
         * Parallel execution, so split the task: 400.0
         * Parallel execution, so split the task: 200.0
         * Sequential execution:100.0
         * Parallel execution, so split the task: 200.0
         * Sequential execution:100.0
         * Parallel execution, so split the task: 200.0
         * Sequential execution:100.0
         * Sequential execution:100.0
         * Sequential execution:100.0
         * Parallel execution, so split the task: 200.0
         * Sequential execution:100.0
         * Sequential execution:100.0
         * Sequential execution:100.0
         * 1600.0
         */
    }
}
