package login_register.login_exceptions;

public class PasswordsDontMatch extends Exception {

    public PasswordsDontMatch(String m) {
        super(m);
    }
}