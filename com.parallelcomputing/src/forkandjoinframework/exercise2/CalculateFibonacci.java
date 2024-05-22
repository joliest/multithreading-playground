package forkandjoinframework.exercise2;

import java.util.concurrent.RecursiveTask;

public class CalculateFibonacci extends RecursiveTask<Integer> {
    private int num;

    public CalculateFibonacci(int num) {
        this.num = num;
    }

    @Override
    protected Integer compute() {
        if (num <= 1) {
            return num;
        }

        CalculateFibonacci cf1 = new CalculateFibonacci(num - 1);
        CalculateFibonacci cf2 = new CalculateFibonacci(num - 2);

//        cf1.fork();
        cf2.fork();

//        int num1 = cf1.join();
        // (instead of fork()) compute() will allow actual thread to execute the first cf1 (reusing the actual thread)
        // preventing actual thread to become idle while it waits for its sub-thread to finish
        // compute() is useful for thread optimisation
        int num1 = cf1.compute();
        // will still become a distinct thread
        int num2 = cf2.join();
        int sum = num1 + num2;
        System.out.println(String.format("%s+%s=%s", num1, num2, sum));

        return sum;
    }
}
