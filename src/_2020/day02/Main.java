package _2020.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Password> mappedPasswords = getLinesFromFile().stream()
                .map(Main::getMappedInput)
                .collect(Collectors.toList());

        RangeValidator rangeValidator = new RangeValidator();
        long validRange = mappedPasswords.stream()
                .filter(rangeValidator::validPassword)
                .count();

        PositionValidator positionValidator = new PositionValidator();
        long validPosition = mappedPasswords.stream()
                .filter(positionValidator::validPassword)
                .count();

        System.out.println("Valid range: " + validRange);
        System.out.println("Valid position: " + validPosition);
    }

    private static List<String> getLinesFromFile() throws IOException {
        String fileName = "src/_2020/day02/input.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());
        }
    }

    private static Password getMappedInput(String inputLine) {
        String[] tokens = inputLine.split("\\s+");
        int[] numRange = Arrays.stream(tokens[0].split("-")).mapToInt(Integer::valueOf).toArray();
        char c = tokens[1].charAt(0);
        String pass = tokens[2];
        return Password.from(numRange[0], numRange[1], c, pass);
    }
}
