import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockWorker {
    private Lock lock = new ReentrantLock();

    // Returns a new Condition instance that is bound to this Lock instance.
    // allow us to have a functionality like wait() and notify()
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Producer method. . .");

        // similar with wait() method
        condition.await();
        System.out.println("Again with the producer method. . .");

        // of course, DONT FORGET THIS!
        lock.unlock();
    }

    public void consume() throws InterruptedException {
        // we make sure producer() will aquire the lock first
        Thread.sleep(2000);
        lock.lock();
        System.out.println("Consumer method . . .");
        Thread.sleep(3000);

        // similar with notify()
        // wakes up one waiting thread
        condition.signal();

        // of course, DONT FORGET THIS!
        lock.unlock();
    }
}

public class AppReentrantLockForProduceAndConsume {
    public static void main(String[] args) {
        LockWorker worker = new LockWorker();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

//        System.out.println(counter);
    }
}
