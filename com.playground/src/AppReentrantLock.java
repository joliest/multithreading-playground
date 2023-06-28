import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AppReentrantLock {
    private static int counter = 0;

    // Lock is an interface
    private static Lock lock = new ReentrantLock();

    private static void increment() {
        // acquires the lock
        // If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until the lock has been acquired
        lock.lock();
        try {
            // CRUCIAL: if there's an exception, it wont unlock the counter,
            // we need to use try-catch-finally to always unlock if theres unexpected error
            for (int i = 0; i < 10000; i++)
                counter++;
        } finally {
            // thread releases the given thread
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter);
    }
}
