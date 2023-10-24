import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ProcessorForCallable implements Callable<String> {

    private int id;
    public ProcessorForCallable(int id) {
        this.id = id;
    }

    @Override
    // returns a String as we specify the <T> in callable
    public String call() throws Exception {
        // for demo purposes
        Thread.sleep(2000);
        return "Id: " + id;
    }
}

public class AppCallableInterfaceImpl {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // use submit() for callable instead of execute()
            // use Future (Crucial) and make returning value as <T>
            Future<String> future = service.submit(new ProcessorForCallable(i + 1));
            list.add(future);
        }

        for (Future<String> f: list) {
            // get() will allow you to retrieve the result (String)
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
