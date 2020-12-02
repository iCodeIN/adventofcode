package _2020.day02;

public class RangeValidator implements PasswordValidator {
    @Override
    public boolean validPassword(Password p) {
        long validChars = getNumberOfMatchedCharsInLine(p.getPassword(), p.getCharacter());
        return validChars >= p.getFirst() && validChars <= p.getSecond();
    }

    private static long getNumberOfMatchedCharsInLine(String line, char inputChar) {
        return line.chars().filter(c -> c == inputChar).count();
    }
}
