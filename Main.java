/*
 * 
 * 
 * For now only use of this class is for 
 * testing program's functionality
 * 
 * 
 */

import user_and_admin.*;
import database.UserDataBase;
import login_register.Login;
import gui_log_reg.LoginFrame;

class Main {

    public static void main(String[] args) {

        User user1 = User.createUser("JohnWick", "123456789");
        User user2 = User.createUser("useruser", "123456789");
        User user3 = User.createUser("Arnold", "123456789");

        System.out.println(user1.toString());

        UserDataBase.UserDataBase1.add(user1);

        System.out.println(UserDataBase.UserDataBase1.isInMap(user1.getUsername()));

        System.out.println(Login.tryLogin("Orkhan", "123456789", false));
        System.out.println(Login.tryLogin("JohnWick", "123456789", false));
        LoginFrame.Login();
    }
}