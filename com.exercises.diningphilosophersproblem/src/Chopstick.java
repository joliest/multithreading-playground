import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//Notes: https://docs.google.com/document/d/11qtndtQ78To4IwnWHOELc21LpDaK4DYW4cl7ie9uWKM/edit#heading=h.vowfyuoblstc
public class Chopstick {
    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    // if the given philosopher can pick up this chopstick
    public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {
        // this is weather we will simulate deadlock
        // use tryLock() instead of lock()
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philosopher + " picked up " + state.toString() + " " + this);
            return true;
        }
        return false;
    }
    // if the given philosopher puts this chopstick down
    public void putDown(Philosopher philosopher, State state) {
        // release this chopstick
        lock.unlock();
        System.out.println(philosopher + " puts down " + state.toString() + " " + this);
    }

    @Override
    public String toString() {
        return "Chopstick 🥢" + id;
    }
}
