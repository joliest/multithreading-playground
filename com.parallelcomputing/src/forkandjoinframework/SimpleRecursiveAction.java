package forkandjoinframework;

import java.util.concurrent.RecursiveAction;

// we are not returning any value in RecursiveAction
public class SimpleRecursiveAction extends RecursiveAction {
    private int simulatedWork;
    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {
        // if the task is too large, we split and execute the task in parallel
        // if task is too small, execute it on sequential approach
        if (simulatedWork > 100) {
            System.out.println("Parallel execution and split the tasks . . .");

            // split the tasks
            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork/2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork/2);

            // OPTION 1
            // asynchronously executes this tasks in the pool
            action1.fork();
            action2.fork();

            // notify the task to finish execution
            // returns the result of the computation when it’s finished
            action1.join();
            action2.join();

            // OPTION 2 (works the same as Option 1)
            // we can use invokeAll to simplify OPTION 1
            // waits for these action to complete execution
            invokeAll(action1, action2);
        } else {
            System.out.println("Sequential execution and split the tasks . . .");
            System.out.println("The size of this task " + simulatedWork);
        }
    }
}
