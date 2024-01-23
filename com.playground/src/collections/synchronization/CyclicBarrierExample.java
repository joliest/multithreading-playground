package collections.synchronization;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CyclicBarrierWorker implements Runnable {
    private int id;
    private Random random;
    private CyclicBarrier barrier;

    public CyclicBarrierWorker(int id, CyclicBarrier barrier) {
        this.id = id;
        this.random = new Random();
        this.barrier = barrier;
    }


    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID " + this.id + " starts the work...");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // when all threads call await(), this is when
            // the "barrier is broken"
            this.barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // reset() is called
            e.printStackTrace();
        }
        System.out.println("After the await()..., " + id + " will continue on whatever it is doing");
    }
}

public class CyclicBarrierExample {
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(5);

        // define number of parties, number of threads we are dealing with
        // define a runnable interface that will run automatically when count reaches 0
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            // will run when count reaches 0
            // when all threads call barrier.await()
            @Override
            public void run() {
                System.out.println("All task have been finished!");

                // after this all thread will run the codes after the barriers.await() vvv
//                Thread with ID 1 starts the work...
//                Thread with ID 3 starts the work...
//                Thread with ID 4 starts the work...
//                Thread with ID 2 starts the work...
//                Thread with ID 5 starts the work...
//                All task have been finished!
//                        After the await()..., 2 will continue on whatever it is doing
//                After the await()..., 1 will continue on whatever it is doing
//                After the await()..., 4 will continue on whatever it is doing
//                After the await()..., 5 will continue on whatever it is doing
//                After the await()..., 3 will continue on whatever it is doing

            }
        });

        for (int i = 0; i < 5; i++) {
            service.execute(new CyclicBarrierWorker(i + 1, barrier));
        }
        service.shutdown();
    }
}
