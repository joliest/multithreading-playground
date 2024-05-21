package forkandjoinframework.exercise1;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class PrintingIntegerRecursiveAction extends RecursiveAction {
    private final List<Integer> nums;
    public PrintingIntegerRecursiveAction(List<Integer> nums) {
        this.nums = nums;
    }

    @Override
    protected void compute() {
        if (nums.size() > 2) {
            // otherwise we split the problem into 2 sub-problems
            // [a,b,c] --> [a] and [b,c]
            // [a,b,c,d] --> [a,b] and [c,d]
            List<Integer> left = nums.subList(0, nums.size()/2);
            List<Integer> right = nums.subList(nums.size()/2,  nums.size());
            PrintingIntegerRecursiveAction action1 = new PrintingIntegerRecursiveAction(left);
            PrintingIntegerRecursiveAction action2 = new PrintingIntegerRecursiveAction(right);

            invokeAll(action1, action2);
        } else {
            for (Integer i: nums) {
                System.out.println(i);
            }
        }
    }
}
