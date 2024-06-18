package structuredconcurrency;

import java.time.Duration;
import java.util.concurrent.Callable;

public class LongProcessShutdownOnFailure implements Callable<String> {
    private int timeToSleep;
    private String result;
    private boolean fail;

    public LongProcessShutdownOnFailure(int timeToSleep, String result, boolean fail) {
        this.timeToSleep = timeToSleep;
        this.result = result;
        this.fail = fail;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Starting thread :" + result);
        Thread.sleep(Duration.ofSeconds(timeToSleep));

        if (fail) {
            System.out.println("Failure in child thread :" + result);
            throw new RuntimeException("Error");
        }


        System.out.println("Finished thread :" + result);
        return result;
    }
}
