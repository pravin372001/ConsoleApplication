package com.zsgs.librarymanagement.login;

import com.zsgs.librarymanagement.Librarydatabase.LibraryDatabase;
import com.zsgs.librarymanagement.model.Admin;
import com.zsgs.librarymanagement.validator.Validator;

public class LoginModel {
    LoginView loginView;
    Admin admin = LibraryDatabase.getInstance().getAdmin();

    LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    private boolean validatePass(String passWord) {
        return Validator.validatePassword(passWord);
    }

    public boolean validateUser(String userName, String password) {
        if (!validatePass(password)) {
            loginView.showMessage("Invalid Format of Password");
            loginView.init();
            return false;
        }
        if (isValidUser(userName)) {
            if (isValidPassword(password)) {
                loginView.onSuccess();
                return true;
            } else {
                loginView.showMessage("Invalid Password");
            }
        } else {
            loginView.showMessage("Invalid User Name");
        }
        return false;
    }

    private boolean isValidUser(String userName) {
        return userName.equals(admin.getCredential().getUserName());
    }

    private boolean isValidPassword(String password) {
        return password.equals(admin.getCredential().getPassword());
    }
}
