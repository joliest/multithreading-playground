import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AppDeadlockExamples {

    // nned two or more locks to replciate deadlock
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        // thread using lambda

        AppDeadlockExamples deadlock = new AppDeadlockExamples();

        // second arg is "name" of the thread
        // With this approach, we no longer need to define new Runnable and implement run() method
        // deadlock::worker1 is similar with () -> deadlock.worker1()
        new Thread(deadlock::worker1, "worker1").start();
        new Thread(deadlock::worker2, "worker2").start();
    }

    public void worker1() {
        lock1.lock();
        System.out.println("Worker1 acquires the lock1. . .");

        try {
            // make sure second worker will acquire the lock
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println("Worker1 acquired the lock2. . . ");

        lock1.unlock();
        lock2.unlock();
    }


    public void worker2() {
        /**
         TODO: uncomment to cause deadlock (comment-out the non deadlock as well)
         lock2.lock();
         System.out.println("Worker2 acquires the lock2. . .");
         */
        lock1.lock();
        System.out.println("Worker2 acquires the lock1. . .");

        // make sure second worker will acquire the lock
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         TODO: uncomment to cause deadlock (comment-out the non deadlock as well)
         lock1.lock();
         System.out.println("Worker2 acquired the lock1. . . ");
         */
        lock2.lock();
        System.out.println("Worker2 acquired the lock2. . . ");

        lock1.unlock();
        lock2.unlock();

    }
}
