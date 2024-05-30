package parallelization.savingpersonapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelSaveOperationApp {
    public static final String DIRECTORY = System.getProperty("user.dir") + "/tests/";

    public static void main(String[] args) throws IOException {

        // create a directory
        Files.createDirectories(Paths.get(DIRECTORY));

        ParallelSaveOperationApp app = new ParallelSaveOperationApp();
        List<Person> people = app.generatePeople(100000);

        // sequential approach
        long start = System.currentTimeMillis();
        people.stream().forEach(ParallelSaveOperationApp::save);
        System.out.println("Time taken sequential: " + (System.currentTimeMillis() - start));

        // parallel approach
        start = System.currentTimeMillis();
        people.parallelStream().forEach(ParallelSaveOperationApp::save);
        System.out.println("Time taken parallel: " + (System.currentTimeMillis() - start));
    }

    private static void save(Person person) {
        try (FileOutputStream fos =
                     new FileOutputStream(new File(DIRECTORY + person.getPersonId() + ".txt"))){

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Person> generatePeople(int num) {
        return Stream.iterate(0, n -> n + 1)
                .limit(num)
                .map(Person::new)
                .collect(Collectors.toList());
    }
}
