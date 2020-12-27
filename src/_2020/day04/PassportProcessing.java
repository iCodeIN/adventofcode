package _2020.day04;

import _2020.FileReader;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PassportProcessing {
    private static final String FILE_LOCATION = "src/_2020/day04/input.txt";
    private static final Set<String> KEYS = new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));

    public static void main(String[] args) throws IOException {
        String[] lines = FileReader.getFileAsString(Paths.get(FILE_LOCATION)).split("\r\n\r\n");

        List<String> result = Arrays.stream(lines)
                .filter(PassportProcessing::validPassports)
                .collect(Collectors.toList());

        System.out.println(result.size());
    }

    private static boolean validPassports(String passport) {
        Set<String> keys = Arrays.stream(passport.split("\\s+|\r\n"))
                .map(PassportProcessing::getKeys)
                .collect(Collectors.toSet());
        return keys.containsAll(KEYS);
    }

    private static String getKeys(String token) {
        return token.split(":")[0];
    }

}