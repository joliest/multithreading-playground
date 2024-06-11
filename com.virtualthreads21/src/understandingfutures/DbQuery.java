package understandingfutures;

import java.util.concurrent.Callable;

public class DbQuery {
    public static String run() {
        System.out.println("DB Operation started . . . ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("DB Operation finished . . . ");

        return "DB Query result";
    }
}
