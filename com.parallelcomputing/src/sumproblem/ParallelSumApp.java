package sumproblem;

public class ParallelSumApp {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,3,3,2,12,2,2,1,2,121,21,21,21,21,2,21,21};
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        ParallelSum parallelSum = new ParallelSum(numOfThreads);
        System.out.println(parallelSum.sum(nums));
    }
}
