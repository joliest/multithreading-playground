import java.util.concurrent.atomic.AtomicInteger;

public class AppAtomicVariableInteger {
    private static AtomicInteger counter = new AtomicInteger(0);

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
            // we will wait for the two threads to finish execution
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter: " + counter);
    }

    public static void increment() {
        for(int i = 0; i < 10000; i++)
            counter.getAndIncrement();
    }
}
