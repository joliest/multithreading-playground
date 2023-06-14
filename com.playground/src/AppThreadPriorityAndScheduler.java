class Worker implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}

public class AppThreadPriorityAndScheduler {
    public static void main(String[] args) {
        Thread t = new Thread(new Worker());
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();

        // main thread with a default priority of 5
        System.out.println("This is the main thread. . . ");
    }
}
