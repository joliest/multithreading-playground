package sumproblem;

public class ParallelSum {
    private ParallelWorker[] workers;
    private int numOfThreads;
    public ParallelSum(int numberOfThreads) {
        this.numOfThreads = numberOfThreads;
        this.workers = new ParallelWorker[numberOfThreads];
    }

    public int sum(int[] nums) {
        // split array to multiple sub array
        int size = (int) Math.ceil(nums.length * 1.0 / numOfThreads);
        for (int i = 0; i < numOfThreads; i++) {
            workers[i] = new ParallelWorker(nums, i*size, (i+1) * size);
            workers[i].start();
        }

        try {
            for (ParallelWorker worker: this.workers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int total = 0;
        for (ParallelWorker worker: workers) {
            total += worker.getPartialSum();
        }
        return total;
    }
}
