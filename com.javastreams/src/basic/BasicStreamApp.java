package basic;

import java.util.Arrays;

public class BasicStreamApp {
    public static void main(String[] args) {
        int[] nums = {1,5,3,-2,9,12};
        Arrays.stream(nums).forEach(System.out::println);
    }
}
