import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    private int id;
    private Lock lock;

    public Book(int id) {
        this.id = id;
        // 'true' means it will lock the longest waiting thread. making sure that all threads will be handled
        this.lock = new ReentrantLock();
    }

    public void read (Student student) throws InterruptedException {
        // use tryLock with if/else statements
        // try to acquire the book in 10 seconds
//        if (lock.tryLock(10, TimeUnit.SECONDS)) {
//            System.out.println("👨🏻‍🎓 " + student + " starts reading 📘" + this);
//            // student reads it for 2 seconds
//            Thread.sleep(2000);
//            lock.unlock();
//            System.out.println("👨🏻‍🎓 " + student + " has just finished reading 📘" + this);
//        }

        // other solution without if statement is using lock()
        // this will guarantee that given thread will wait for the Reentrant lock no matter what
        // it will be blocked if lock is not unlocked, making sure no two students reading the same book
        lock.lock();
        System.out.println(student + " starts reading" + this);
        // student reads it for 2 seconds
        Thread.sleep(2000);
        lock.unlock();
        System.out.println(student + " has just finished reading" + this);
    }

    @Override
    public String toString() {
        return "📘 Book #" + id;
    }
}
