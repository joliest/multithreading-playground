package collections.synchronization;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FirstBlockQueueWorker implements Runnable {
    private BlockingQueue<Integer> queue;
    public FirstBlockQueueWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int counter = 0;

        // infinite loop
        while (true) {
            try {
                queue.put(counter);
                System.out.println("Putting/Inserting item into the queue . . . " + counter);
                counter++;
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}class SecondBlockQueueWorker implements Runnable {
    private BlockingQueue<Integer> queue;
    public SecondBlockQueueWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        // infinite loop
        while (true) {
            try {
                int counter = queue.take();
                System.out.println("Taking/Removing item from the queue . . . " + counter);
                queue.put(counter);
                counter++;

//                we will going to remove items more frequently than taking it
                // removes thread per 100ms
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        FirstBlockQueueWorker firstWorker = new FirstBlockQueueWorker(queue);
        SecondBlockQueueWorker secondWorker = new SecondBlockQueueWorker(queue);

        // keeps inserting items on queue blocking queue
        new Thread(firstWorker).start();
        // keeps removing items on queue blocking queue
        new Thread(secondWorker).start();
    }
}
