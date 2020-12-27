package _2020.day04;

import _2020.FileReader;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PassportProcessing {
    private static final String FILE_LOCATION = "src/_2020/day04/input.txt";
    private static final Set<String> KEYS = new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
    private static final Set<String> EYE_COLORS = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));

    public static void main(String[] args) throws IOException {
        String[] lines = FileReader.getFileAsString(Paths.get(FILE_LOCATION)).split("\r\n\r\n");

        // Part One
        System.out.println(Arrays.stream(lines)
                .filter(PassportProcessing::validPassportKeys)
                .count());

        // Part Two
        System.out.println(Arrays.stream(lines)
                .filter(PassportProcessing::validPassport)
                .count());
    }

    private static boolean validPassportKeys(String passportLine) {
        Set<String> keys = Arrays.stream(passportLine.split("\\s+|\r\n"))
                .map(PassportProcessing::getKeys)
                .collect(Collectors.toSet());
        return keys.containsAll(KEYS);
    }

    private static String getKeys(String token) {
        return token.split(":")[0];
    }

    private static boolean validPassport(String passportLine) {
        Map<String, String> passportData = Arrays.stream(passportLine.split("\\s+|\r\n"))
                .map(PassportProcessing::getPairs)
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));

        return passportData.keySet().containsAll(KEYS) && allValuesAreValid(passportData);
    }

    private static boolean allValuesAreValid(Map<String, String> passportData) {
        try {
            return isValidBirth(passportData.get("byr"))
                    && isValidIssue(passportData.get("iyr"))
                    && isValidExpirationYear(passportData.get("eyr"))
                    && isValidHeight(passportData.get("hgt"))
                    && isValidHairColor(passportData.get("hcl"))
                    && isValidEyeColor(passportData.get("ecl"))
                    && isValidPassportID(passportData.get("pid"));
        } catch (Exception ignored) {
        }
        return false;
    }

    private static boolean isValidBirth(String birth) {
        return validateRange(birth, 1920, 2002);
    }

    private static boolean isValidIssue(String issue) {
        return validateRange(issue, 2010, 2020);
    }

    private static boolean isValidExpirationYear(String expirationYear) {
        return validateRange(expirationYear, 2020, 2030);
    }

    private static boolean validateRange(String number, int lowerBound, int upperBound) {
        int year = Integer.parseInt(number);
        return year >= lowerBound && year <= upperBound;
    }

    private static boolean isValidHeight(String height) {
        int years = Integer.parseInt(height.substring(0, height.length() - 2));
        String unit = height.substring(height.length() - 2);
        return (unit.equals("cm") && years >= 150 && years <= 193) || (unit.equals("in") && years >= 59 && years <= 76);
    }

    private static boolean isValidHairColor(String hairColor) {
        return hairColor.matches("#[0-9a-f]{6}");
    }

    private static boolean isValidEyeColor(String eyeColor) {
        return EYE_COLORS.contains(eyeColor);
    }

    private static boolean isValidPassportID(String passportID) {
        return passportID.length() == 9 && passportID.matches("[0-9]+");
    }

    private static String[] getPairs(String token) {
        return token.split(":");
    }
}