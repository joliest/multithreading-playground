package basic;

public class VirtualTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Started ..." + Thread.currentThread().getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished ..." + Thread.currentThread().getName());
    }
}
