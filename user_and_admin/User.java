package user_and_admin;

import java.math.BigInteger;

import user_and_admin.AbstractUser;
import user_and_admin.exceptions.IllegalPasswordException;
import user_and_admin.exceptions.IllegalUsernameException;

public class User extends AbstractUser {
    private static BigInteger code = new BigInteger("00000000");

    public User(String username, String password) throws IllegalUsernameException, IllegalPasswordException {
        super(username, password);
    }

    // User instance can only be created by createUser() method.
    public User createUser(String username, String password) {
        User user = null;
        try {
            user = new User(username, password);
        } catch (IllegalUsernameException | IllegalPasswordException e) {
            System.out.println(e);
        }
        return user;
    }

    public String toString() {
        return "[ username: " + super.getUsername() + "]";
    }
}
