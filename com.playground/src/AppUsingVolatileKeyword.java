class VolatileWorker implements Runnable {

    // CPU will not going to cache this variable
    // this variable will be stored in the main memory
    // 1. variables is possible to be stored in the main memory without the volatile keyword
    // 2. both of the threads may use the same cache as well
    // we usually use 'volatile' just to make sure that this variable will be stored in main memory
    // and can be visible to other threads of the application
    private volatile boolean terminated;

    @Override
    public void run() {
        while(!terminated) {
            System.out.println("Working class is running . . . ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}

public class AppUsingVolatileKeyword {
    public static void main(String[] args) {
        VolatileWorker worker = new VolatileWorker();
        Thread t1 = new Thread(worker);

        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        worker.setTerminated(true);
        System.out.println("Algorithm is terminated . . ");

    }
}
