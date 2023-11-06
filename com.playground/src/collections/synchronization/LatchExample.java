package collections.synchronization;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LatchWorker implements Runnable {
    private int id;
    private CountDownLatch latch;

    public LatchWorker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        // decrements count of the latch, releasing all waiting threads
        latch.countDown();
    }

    private void doWork() {
        try {
            System.out.println("Thread with ID " + this.id + " starts working");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class LatchExample {
    public static void main(String[] args) {
        // define the counter
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++)
            service.execute(new LatchWorker(i, latch));

        try {
            // wait until latch has counted down to zero (0)
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All Tasks have been finished");
        service.shutdown();
    }
}