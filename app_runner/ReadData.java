package app_runner;

import database_system.BookDataBase;
import database_system.UserDataBase;

public class ReadData {

    public static void read() {

        UserDataBase.loadData();
        BookDataBase.loadData();
    }
}
