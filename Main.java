/*
 * 
 * 
 * For now only use of this class is for 
 * testing program's functionality
 * 
 * 
 */

 import database_systems.BookDataBase;
 import database_systems.UserDataBase;
 import database_systems.exceptions.IllegalMemberException;
 import entities.book.Book;
 import entities.user_and_admin.User;
 import login_register.Login;
 import login_register.Register;
 import gui_log_reg.*;
 import gui_library.DatabaseLib;
 
 import java.io.BufferedReader;
 import java.io.FileInputStream;
 import java.io.FileReader;
 import java.io.IOException;
 
 import javax.swing.JCheckBoxMenuItem;
 
 class Main {
 
     public static void main(String[] args) {
         // UserDataBase.loadData();
 
         // User user1 = User.createUser("user12345", "1234567890");
         // try {
         // UserDataBase.add(user1);
         // } catch (IllegalMemberException e) {
         // System.out.println(e);
         // }
         // System.out.println(UserDataBase.contains(user1.getUsername()));
         // UserDataBase.writeData();
 
         try (BufferedReader br = new BufferedReader(new FileReader("./brodsky.csv"))) {
 
             String str;
             br.readLine();
             while ((str = br.readLine()) != null) {
                 // Added by Orkhan*****************
                 add(str);
             }
         } catch (IOException | IllegalMemberException e) {
             System.out.println(e);
         }
 
         BookDataBase.writeData();
     }
 
     static void add(String line) throws IllegalMemberException {
         if (line.charAt(0) == '\"') {
             String row[] = line.split("\",", -1);
             String[] titles = row[0].replaceAll("\"", "").split(",", -1);
 
             for (int i = 0; i < titles.length; i++) {
                 if (titles[i].equals("")) {
                     titles[i] = "Unknown";
                 } else if (titles[i].startsWith(" ")) {
                     titles[i] = titles[i].replaceFirst(" ", "");
                 }
                 BookDataBase.add(Book.createBook(titles[i], row[1]));
             }
         } else if (line.charAt(line.length() - 1) == '\"') {
             String row[] = line.split(",\"", -1);
             BookDataBase.add(Book.createBook(row[0], row[1].replaceAll("\"", "")));
 
         } else {
             String row[] = line.split(",", -1);
 
             if (row[0].equals("")) {
                 row[0] = "Unknown";
             } else if (row[1].equals("")) {
                 row[1] = "Unknown";
             }
             BookDataBase.add(Book.createBook(row[0], row[1]));
         }
     }
 }