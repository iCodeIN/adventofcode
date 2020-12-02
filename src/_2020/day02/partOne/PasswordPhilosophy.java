package _2020.day02.partOne;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordPhilosophy {
    public static void main(String[] args) throws IOException {
        List<Password> passwords = getPasswordObjectsFromFile();
        long numOfValidPasswords = passwords.stream()
                .filter(PasswordPhilosophy::isValidPassword)
                .count();

        System.out.println(numOfValidPasswords);
    }

    private static boolean isValidPassword(Password p) {
        long validChars = getNumberOfMatchedCharsInLine(p.password, p.character);
        return validChars >= p.min && validChars <= p.max;
    }

    private static long getNumberOfMatchedCharsInLine(String line, char inputChar) {
        return line.chars().filter(c -> c == inputChar).count();
    }

    private static List<Password> getPasswordObjectsFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/_2020/day02/input.txt"));
        String regex = "((?<min>\\d+)\\-(?<max>\\d+))\\s+(?<char>[a-zA-Z]):\\s+(?<string>.*)";
        Pattern pattern = Pattern.compile(regex);

        List<Password> passwords = new ArrayList<>();
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            passwords.add(getMappedInput(inputLine, pattern));
        }
        return passwords;
    }

    private static Password getMappedInput(String inputLine, Pattern pattern) {
        Matcher m = pattern.matcher(inputLine);
        if (m.find()) {
            int min = Integer.parseInt(m.group("min"));
            int max = Integer.parseInt(m.group("max"));
            char character = m.group("char").charAt(0);
            String line = m.group("string");
            return new Password(min, max, character, line);
        }
        return null;
    }

    private static class Password {
        private int min;
        private int max;
        private char character;
        private String password;

        public Password(int min, int max, char character, String password) {
            this.max = max;
            this.min = min;
            this.character = character;
            this.password = password;
        }
    }

}

