package understandingfutures;

public class RestQuery {
    public static String run() {
        System.out.println("Rest Operation started . . . ");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Rest Operation finished . . . ");

        return "Rest Query result";
    }
}
