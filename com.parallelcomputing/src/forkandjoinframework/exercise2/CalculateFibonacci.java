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

        cf1.fork();
        cf2.fork();

        int num1 = cf1.join();
        int num2 = cf2.join();
        int sum = cf1.join() + cf2.join();
        System.out.println(String.format("%s+%s=%s", num1, num2, sum));

        return cf1.join() + cf2.join();
    }
}
