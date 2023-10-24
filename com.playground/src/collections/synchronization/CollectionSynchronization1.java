package collections.synchronization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionSynchronization1 {
    public static void main(String[] args) {

        // add or remove methods are synchronized
        // has intrinsic lock (No two threads will execute the same methood_
        // -- synchronizedList() is not an efficient solution
        // they have to wait for each other even when the want t execute of independent methods
        // efficient solution, need to consider CONCURRENT COLLECTIONS
        List<Integer> nums = Collections.synchronizedList(new ArrayList<>());

        // creating multiple thread
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                nums.add(i);
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                nums.add(i);
        });
        t1.start();
        t2.start();
        try {
            // wait t1 to complete
            t1.join();
            // wait t2 to complete
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Size of array: " + nums.size());
    }
}
