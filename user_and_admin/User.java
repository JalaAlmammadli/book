/*


 * Created by Orkhan
 * 
 * 
 */

package user_and_admin;

import user_and_admin.AbstractUser;
import user_and_admin.exceptions.IllegalPasswordException;
import user_and_admin.exceptions.IllegalUsernameException;

public class User extends AbstractUser {

    private User(String username, String password) throws IllegalUsernameException, IllegalPasswordException {
        super(username, password);
    }

    // User instance can only be created by createUser() method.
    public static User createUser(String username, String password) {
        try {
            User user = new User(username, password);
            return user;
        } catch (IllegalUsernameException | IllegalPasswordException e) {
            System.out.println(e);
        }
        return null;
    }

    public String toString() {
        return "[ username: " + super.getUsername() + " ]";
    }
}
