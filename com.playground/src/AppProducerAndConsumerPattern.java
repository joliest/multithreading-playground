import java.util.ArrayList;
import java.util.List;

class Processor {
    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {
        // inserts integers
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("producer(): Waiting for removing items . . .");

                    // if we dont put the lock, it will wait for Processor's object
                    lock.wait();
                } else {
                    System.out.println("producer(): Adding: " + value);
                    list.add(value);
                    value++;

                    // other thread can be notified if it's only in waiting state
                    // if thread is not waiting, nothing will happen
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }

    }
    public void consumer() throws InterruptedException {
        // removes integers
        synchronized (lock) {
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("consumer(): Waiting for adding items . . .");
                    value = 0;
                    lock.wait();
                } else {
                    System.out.println("consumer(): Removing: " + list.remove(list.size() - 1));

                    // again, it will only notify the other thread if it's in waiting state
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}

public class AppProducerAndConsumerPattern {
    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

    }
}
