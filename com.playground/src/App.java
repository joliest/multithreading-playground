class Runner1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            try {
                // this thread will sleep at 1s, making the execution slower
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner1: " + i);
        }

    }
}

class Runner2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            try {
                // this thread will sleep at 1s
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Runner2: " + i);
        }
    }
}

public class App {
    public static void main(String[] args) {
        Thread t1 = new Runner1();
        Thread t2 = new Runner2();

        // achieving multi threading
        // not a parallel execution
        t1.start();
        t2.start();

        // we can wait for the thread to finish using join() method
        try {
            // waits for t1 and t2 to finish execution
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Finished with threads...");
    }
}
