package login_register.login_exceptions;

public class ExistingUserException extends Exception {

    public ExistingUserException(String m) {
        super(m);
    }
}
