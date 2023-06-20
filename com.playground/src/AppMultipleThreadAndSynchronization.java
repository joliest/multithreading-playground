public class AppMultipleThreadAndSynchronization {
    public static int counter1 = 0;
    public static int counter2 = 0;

    // resolving 1 intrinsic lock per 1 object
    // counter1 and counter 2 will no longer share the same intrinsic lock
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    public static synchronized void increment1() {
        // independent lock, will be locked using lock 1
        // no need for counter2 to complete
        synchronized (lock1) {
            counter1++;
        }
    }
    public static synchronized void increment2() {
        // independent lock
        synchronized (lock2) {
            counter2++;
        }
    }
    public static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; ++i) {
                    increment1();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; ++i) {
                    increment2();
                }
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The counter1 is " + counter1);
        System.out.println("The counter2 is " + counter2);
    }
    public static void main(String[] args) {
        process();
    }
}
