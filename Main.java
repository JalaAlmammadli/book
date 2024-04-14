/*
 * 
 * 
 * For now only use of this class is for 
 * testing program's functionality
 * 
 * 
 */

import user_and_admin.*;
import user_and_admin.exceptions.IllegalPasswordException;
import user_and_admin.exceptions.IllegalUsernameException;
import database.UserDataBase;
import login_register.Login;
import login_register.Register;
import gui_log_reg.*;
import javax.swing.JCheckBoxMenuItem;

class Main {

    public static void main(String[] args) {

        User user1 = User.createUser("JohnWick", "123456789");
        User user2 = User.createUser("useruser", "123456789");
        User user3 = User.createUser("Arnold", "123456789");

        System.out.println(user1.toString());

        UserDataBase.UserDataBase1.add(user1);

        System.out.println(UserDataBase.UserDataBase1.isInMap(user1.getUsername()));

        // try {
        // Register.tryRegister("Orkhan", "123", "123");
        // } catch (IllegalPasswordException | IllegalUsernameException ex) {
        // System.out.println(ex);
        // }

        // merged - orkhan-branch
        LoginFrame.Login();
        RegisterFrame.openRegistrationForm(false);
    }
}