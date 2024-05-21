package forkandjoinframework.exercise1;

import java.util.Arrays;
import java.util.List;

public class PrintingIntegerRecursiveApp {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        PrintingIntegerRecursiveAction action = new PrintingIntegerRecursiveAction(nums);

        action.invoke();
    }
}
