// /*

// * Created by Orkhan
// *
// *
// */

// package database_systems;

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.File;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.TreeMap;

// import database_systems.exceptions.IllegalMemberException;

// public abstract class DataBaseInterface {

// protected static ArrayList<Object> objectList;
// protected static ArrayList<String> nameList;

// // This method removes user from user_map by its username
// public static void remove(String name) {
// if (contains(name)) {

// objectList.remove(objectList.get(nameList.indexOf(name)));
// }

// System.out.println("There is no such user in the map");
// }

// // returns true if user with username "some username"
// // exits in the user_map
// public static boolean contains(String name) {
// return nameList.contains(name) ? true : false;
// }

// // This method will return user
// public static Object getMember(String name) {

// return objectList.get(nameList.indexOf(name));
// }

// public static int size() {
// return objectList.size();
// }
// }