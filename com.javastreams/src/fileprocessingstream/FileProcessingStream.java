package fileprocessingstream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileProcessingStream {
    public static void main(String[] args) throws IOException {
        String path = "/Users/joli/Developer/workspace-learning/java/multithreading-playground/com.javastreams/src/fileprocessingstream/names";

        Stream<String> namesStream = Files.lines(Paths.get(path));

        namesStream.forEach(System.out::println);

        List<String> names = namesStream.collect(Collectors.toList());
        names.forEach(System.out::println);

    }
}
