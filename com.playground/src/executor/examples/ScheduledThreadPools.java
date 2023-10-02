package executor.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdater implements Runnable{

    @Override
    public void run() {
        System.out.println("Updating and downloading stock related data from the web. . .");
    }
}


public class ScheduledThreadPools {
    public static void main(String[] args) {
        int NUM_OF_THREADS = 1;
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(NUM_OF_THREADS);
        /**
         * 1000 - we wait 1 second (1000) before calling StockMarketUpdater
         * 2000 - we need to run it every 2 seconds
         * MILLISECONDS - because we defined 1000 and 2000
         */
        executorService.scheduleAtFixedRate(new StockMarketUpdater(), 1000, 2000, TimeUnit.MILLISECONDS);
    }
}
