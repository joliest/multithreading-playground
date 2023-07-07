import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Implementing Semaphores
 */
// this is a Singleton pattern
enum Downloader {
    INSTANCE;

    // Only three threads will be run at the same time...
    private Semaphore semaphore = new Semaphore(3, true);
    public void download() {
        try {
            // acquire permit if available
            semaphore.acquire();
            downloadData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // always releases the permit
            semaphore.release();
        }
    }

    private void downloadData() {
        try {
            System.out.println("Downloading data from the web . . .");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class AppSemaphoreExample {
    public static void main(String[] args) {
        // create multiple thread - using executors service
        ExecutorService service = Executors.newCachedThreadPool();

        int numberOfThreads = 12;
        for (int i = 0; i < numberOfThreads; i++) {
            // convenient way in executing a thread
            service.execute(new Runnable() {
                @Override
                public void run() {
                    Downloader.INSTANCE.download();
                }
            });
        }
    }
}

