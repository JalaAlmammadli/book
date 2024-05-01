package app_runner;

import database_systems.BookDataBase;
import database_systems.UserDataBase;

public class ReadData {

    public static void read() {

        UserDataBase.MainUserList.loadData();
        BookDataBase.MainBookList.loadData();
    }
}
