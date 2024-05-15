package sumproblem;

public class ParallelWorker extends Thread {
    public ParallelWorker(int[] nums, int low, int high) {
        super();
        this.nums = nums;
        this.low = low;
        this.high = Math.min(nums.length, high);
    }
    private int[] nums;
    private int low;
    private int high;
    private int partialSum;

    @Override
    public void run() {
        partialSum = 0;
        for (int i = low; i < high; i++) {
            partialSum += nums[i];
        }
    }

    public int getPartialSum() {
        return partialSum;
    }
}
