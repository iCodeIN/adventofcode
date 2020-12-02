package _2020.day02;

public class PositionValidator implements PasswordValidator{
    @Override
    public boolean validPassword(Password p) {
        char c = p.getCharacter();
        String pass = p.getPassword();
        return pass.charAt(p.getFirst() - 1) == c ^  pass.charAt(p.getSecond() - 1) == c;
    }
}
