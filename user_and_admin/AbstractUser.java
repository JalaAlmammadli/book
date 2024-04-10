package user_and_admin;

import user_and_admin.exceptions.IllegalPasswordException;
import user_and_admin.exceptions.IllegalUsernameException;

abstract class AbstractUser {
    private String username;
    private String password;

    // UserInterface() constructor does not handles exception, because if any
    // exception will be thrown, user will not be created.
    public AbstractUser(String username, String password) throws IllegalUsernameException, IllegalPasswordException {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws IllegalUsernameException {
        if (username.length() >= 4 && username.length() <= 20 && testUsername(username)) {
            this.username = username;
            return;
        }
        throw new IllegalUsernameException(
                "Username must be longer 4 letters and lesser 20 letters, and it must contain only english letters!");
    }

    // for now username can only contain english letters, and testUsername() method
    // checks if it only contains english letters.
    private boolean testUsername(String username) {

        for (char i : username.toCharArray()) {

            i = Character.toLowerCase(i);
            if (i >= 'a' && i <= 'z') {
                continue;
            }
            return false;
        }
        return true;
    }

    public String getPassword() {
        return password;
    }

    // password only has setter method, as user only can change password not see it.
    public void setPassword(String password) throws IllegalPasswordException {
        if (password.length() >= 8 && password.length() <= 255) {
            this.password = password;
            return;
        }
        throw new IllegalPasswordException("Password must be longer 8 letters and lesser 255 letters!");
    }

    abstract public String toString();
}
