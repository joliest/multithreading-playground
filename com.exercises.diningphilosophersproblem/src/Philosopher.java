import java.util.Random;

public class Philosopher implements Runnable{
    private int id;
    private volatile boolean full;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    // increment this 1 after philosopher has eaten
    private int eatingCounter;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
//            after eating a lot of 1000x we will terminate the loop
            while (!full) {
                think();
                if (leftChopstick.pickUp(this, State.LEFT)) {
                    // the philosopher is able to acquire the left chopstick
                    // attempting to pickup the right chopstick
                    if (rightChopstick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightChopstick.putDown(this, State.RIGHT);
                    }
                    leftChopstick.putDown(this, State.LEFT);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println(this + " is thinking");
        // this philo will think for random of 0 and 1000
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " is eating");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public boolean isFull() {
        return this.full;
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }

    public int getEatingCounter() {
        return eatingCounter;
    }

    public void setEatingCounter(int eatingCounter) {
        this.eatingCounter = eatingCounter;
    }
}
