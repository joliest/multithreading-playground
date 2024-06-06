package platformvsvirtual;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComparisonPlatformVirtual {
    public static void main(String[] args) {
//        Platform Threads
//        for (int i = 0; i < 10000; i++) {
//            Thread.ofPlatform()
//                    .start(() -> {
//                        try {
//                            System.out.println("Started " + Thread.currentThread());
//                            // threads always wait for 5sec to finish,
//                            // might cause OutOfMemoryError when i > 5000
//                            Thread.sleep(Duration.ofSeconds(5));
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    });
//        }

//        Virtual threads
        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 1000000; i++) {
            service.submit(() -> {
                try {
                    System.out.println("Started " + Thread.currentThread());
                    Thread.sleep(Duration.ofSeconds(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
