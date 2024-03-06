import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    // aim of simulation is that it avoids thread starvaton
//    - all threads will be executed by the executer service
    // tryLock() is very convenient way avoiding deadlock and livelock

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;
        Chopstick[] chopsticks = null;

        try {
            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
            chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICK];
            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICK; ++i) {
                chopsticks[i] = new Chopstick(i);
            }

            // execute thread in one by one service
            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; ++i) {
                philosophers[i] = new Philosopher(
                        i, // id
                        chopsticks[i], // left chopstick
                        // why modular operator?
                        // returns the remainder, (creates the list of c0, c1..., see figure here https://docs.google.com/document/d/11qtndtQ78To4IwnWHOELc21LpDaK4DYW4cl7ie9uWKM/edit#heading=h.oewe98pjbtzc)
                        chopsticks[(i + 1) % Constants.NUMBER_OF_CHOPSTICK] // right chopstick
                );
                // executes run method of the philosphers
                executorService.execute(philosophers[i]);
            }
            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            for(Philosopher philosopher: philosophers) {
                // breaks the infinite while loop in Philosophers
                philosopher.setFull(true);
            }
        } finally {
            // no more execute method will run
            executorService.shutdown();

            while (!executorService.isTerminated()) {
                // we wait 1 second and try again
                Thread.sleep(1000);

                for(Philosopher philosopher: philosophers) {
                    System.out.println(philosopher + " eat # " + philosopher.getEatingCounter() + " times!");
                }

            }
        }
    }
}
// continue 9:48