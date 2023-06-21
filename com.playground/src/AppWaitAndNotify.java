class Process {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("produce(): Running the produce method . . . ");
            System.out.println("produce(): Let's wait till the consume method notifies us . . . ");
            // it will release the intrinsic lock, consume will acquire it
            wait();
            System.out.println("produce(): Again in the producer method....");
        }

    }
    public void consume() throws InterruptedException {
        // makes sure the produce() method enters the synchronized() block first
        Thread.sleep(1000);

        // they will get the same intrinsic lock of Process class
        synchronized (this) {
            System.out.println("consumer(): Consume method is executed . . . ");
            System.out.println("consumer(): Let's notify the producer method . . . ");

            // notify the produce so that it can continue with whatever its doing
            // it will release the thread so that synchronized block that called wait() methods can aqcuire the intrinsic method
            notify();
        }
    }
}

public class AppWaitAndNotify {
    public static void main(String[] args) {
        Process process = new Process();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
