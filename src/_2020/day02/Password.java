package _2020.day02;

public class Password {
    private int first;
    private int second;
    private char character;
    private String password;

    private Password(char character, String password, int first, int second) {
        this.character = character;
        this.password = password;
        this.first = first;
        this.second = second;
    }

    public static Password from(int first, int second, char character, String password) {
        return new Password(character, password, first, second);
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public char getCharacter() {
        return character;
    }

    public String getPassword() {
        return password;
    }

}