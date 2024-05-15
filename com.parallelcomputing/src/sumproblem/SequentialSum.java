package sumproblem;

public class SequentialSum {
    // linear running algorithm O(N)
    public int sum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
