/*
 * 
 * 
 * For now only use of this class is for 
 * testing program's functionality
 * 
 * 
 */

import database.BookDataBase;
import database.UserDataBase;
import entities.book.Book;
import entities.user_and_admin.*;
import entities.user_and_admin.exceptions.IllegalPasswordException;
import entities.user_and_admin.exceptions.IllegalUsernameException;
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

        // User user1 = User.createUser("JohnWick", "123456789");
        // User user2 = User.createUser("useruser", "123456789");
        // User user3 = User.createUser("Arnold", "123456780");

        // System.out.println(user1.toString());

        // UserDataBase.add(user1);
        // UserDataBase.add(user2);
        // UserDataBase.add(user3);

        // System.out.println(UserDataBase.isInMap(user1.getUsername()));
        // System.out.println(UserDataBase.isInMap(user2.getUsername()));
        // System.out.println(UserDataBase.isInMap(user3.getUsername()));

        // UserDataBase.loadData();

        Book book1 = Book.createBook("Title1", "Author1");
        Book book2 = Book.createBook("Title2", "Author2");
        Book book3 = Book.createBook("Title3", "Author3");

        System.out.println(BookDataBase.isInMap("Title1"));

        BookDataBase.add(book1);
        BookDataBase.add(book2);

        BookDataBase.loadData();

        BookDataBase.add(book3);

        System.out.println(BookDataBase.isInMap("Title1"));
        System.out.println(BookDataBase.isInMap("Title2"));
        System.out.println(BookDataBase.isInMap("Title3"));
    }
}