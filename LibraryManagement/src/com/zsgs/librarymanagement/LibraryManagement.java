package com.zsgs.librarymanagement;

import com.zsgs.librarymanagement.Librarydatabase.LibraryDatabase;
import com.zsgs.librarymanagement.login.LoginView;

public class LibraryManagement {
    private static LibraryManagement libraryManagement;

    private String appName = "Library Management System";

    private String appVersion = "0.1.0";

    private LibraryManagement() {

    }

    public static LibraryManagement getInstance() {
        if (libraryManagement == null) {
            libraryManagement = new LibraryManagement();
        }
        return libraryManagement;
    }

    private void create() {
        LoginView login = new LoginView();
        if (!login.init()) {
            login.init();
        }
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to " + LibraryManagement.getInstance().getAppName() + " version "
                + LibraryManagement.getInstance().getAppVersion());
        LibraryManagement.getInstance().initDb();
        LibraryManagement.getInstance().create();
    }

    private void initDb() {
        LibraryDatabase.getInstance().getDataFromDB();
    }
}
