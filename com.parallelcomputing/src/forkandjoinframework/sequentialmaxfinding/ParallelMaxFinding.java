package forkandjoinframework.sequentialmaxfinding;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFinding extends RecursiveTask<Long> {
    private long[] nums;
    private int lowIndex;
    private int highIndex;

//    [1,2,3,4, 5,6,7,8]
//    ^               ^
//    lowIndex       highIndex

//    [1, 2, 3, 4]    [5,6,7,8]
//    ^         ^       ...
//    lowIndex  highIndex

    public ParallelMaxFinding(long[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Long compute() {
        // if array is small, use sequential approach
//        if n is a much more larger, it parallel will become faster
//        if n is too small, sequential will become faster
        if (highIndex - lowIndex < 3000) {
            return sequentialMaxFinding();
        } else {
            // we use parallezisation
            int middleIndex = (highIndex + lowIndex) / 2;
//            [1,2,3,4]    [5,6,7,8]
//            ↑
            ParallelMaxFinding task1 = new ParallelMaxFinding(nums, lowIndex, middleIndex);
//            [1,2,3,4]    [5,6,7,8]
//                          ↑
            ParallelMaxFinding task2 = new ParallelMaxFinding(nums, middleIndex + 1, highIndex);

            invokeAll(task1, task2);

            // join() returns the value of the task
            return Math.max(task1.join(), task2.join());
        }
    }

    private Long sequentialMaxFinding() {
        long max = nums[lowIndex];

        // lowIndex + 1 because max is already using 0
        for (int i = lowIndex + 1; i < highIndex; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}
