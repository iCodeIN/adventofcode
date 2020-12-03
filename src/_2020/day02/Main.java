package _2020.day02;

import _2020.FileReader;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String FILE_LOCATION = "src/_2020/day02/input.txt";

    public static void main(String[] args) throws IOException {
        List<Password> mappedPasswords = FileReader.getLinesFromFile(Paths.get(FILE_LOCATION)).stream()
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

    private static Password getMappedInput(String inputLine) {
        String[] tokens = inputLine.split("\\s+");
        int[] numRange = Arrays.stream(tokens[0].split("-")).mapToInt(Integer::valueOf).toArray();
        char c = tokens[1].charAt(0);
        String pass = tokens[2];
        return Password.from(numRange[0], numRange[1], c, pass);
    }
}
