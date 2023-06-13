class DaemonWorker implements Runnable {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Daemon thread is running . . ");
        }
    }
}
class NormalWorker implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Normal thread is running . . ");
    }
}

public class AppDaemonAndWorkerThreads {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new DaemonWorker());
        Thread normalThread = new Thread(new NormalWorker());

        // marking the thread as Daemon
        daemonThread.setDaemon(true);

        // running the threads
        daemonThread.start();
        normalThread.start();
    }
}
