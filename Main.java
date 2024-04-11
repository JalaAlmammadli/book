
import user_and_admin.*;
import user_and_admin.exceptions.IllegalPasswordException;
import user_and_admin.exceptions.IllegalUsernameException;
import database.UserDataBase;
import login_register.Login;

class Main {

    // For now only use of this class is for testing program's functionality
    public static void main(String[] args) {

        User user1 = User.createUser("Orkhan", "123456789");
        User user2 = User.createUser("useruser", "123456789");
        User user3 = User.createUser("Arnold", "123456789");

        System.out.println(user1.toString());

        UserDataBase.UserDataBase1.add(user1);

        System.out.println(UserDataBase.UserDataBase1.isInMap(user1.getUsername()));

        // System.out.println(Login.tryLogin("Orkhan", "123456789", false));
        System.out.println(Login.tryLogin("Orkhan", "wrong_password", false));
    }
}