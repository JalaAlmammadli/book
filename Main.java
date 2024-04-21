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
import gui_table.Table;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JCheckBoxMenuItem;

class Main {

    public static void main(String[] args) {

        User user1 = User.createUser("JohnWick", "123456789");
        User user2 = User.createUser("useruser", "123456789");
        User user3 = User.createUser("Arnold", "123456789");

        System.out.println(user1.toString());

        UserDataBase.add(user1);
        UserDataBase.add(user2);
        UserDataBase.add(user3);

        System.out.println(UserDataBase.isInMap(user1.getUsername()));
        System.out.println(UserDataBase.isInMap(user2.getUsername()));
        System.out.println(UserDataBase.isInMap(user3.getUsername()));

        // try {
        // Register.tryRegister("Orkhan", "123", "123");
        // } catch (IllegalPasswordException | IllegalUsernameException ex) {
        // System.out.println(ex);
        // }

        // RegisterFrame.openRegistrationForm(true);
        // LoginFrame.Login();
        UserDataBase.loadData();
        RegisterFrame.Register(false);
    }
}