/*
 * 
 * 
 * For now only use of this class is for 
 * testing program's functionality
 * 
 * 
 */

import user_and_admin.*;
<<<<<<< HEAD
import user_and_admin.exceptions.IllegalPasswordException;
import user_and_admin.exceptions.IllegalUsernameException;

import database.UserDataBase;
=======
import database.UserDataBase;
import login_register.Login;
import gui_log_reg.*;
>>>>>>> orkhan-branch

class Main {

    public static void main(String[] args) {

<<<<<<< HEAD
        // User user1 = User.createUser("Orkhan", "123456789");
        // User user2 = User.createUser("useruser", "123456789");
        // User user3 = User.createUser("Arnold", "123456789");
=======
        User user1 = User.createUser("JohnWick", "123456789");
        User user2 = User.createUser("useruser", "123456789");
        User user3 = User.createUser("Arnold", "123456789");
>>>>>>> orkhan-branch

        // System.out.println(user1.toString());

<<<<<<< HEAD
        // UserDataBase.add(user1);

        // System.out.println(UserDataBase.isInMap(user1.getUsername()));
=======
        UserDataBase.UserDataBase1.add(user1);

        System.out.println(UserDataBase.UserDataBase1.isInMap(user1.getUsername()));

        // LoginFrame.Login();
        RegisterFrame.openRegistrationForm();
>>>>>>> orkhan-branch
    }
}