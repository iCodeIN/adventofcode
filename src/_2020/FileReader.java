package _2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    public static List<String> getLinesFromFile(Path path) throws IOException {
        try (Stream<String> stream = Files.lines(path)) {
            return stream.collect(Collectors.toList());
        }
    }
}
