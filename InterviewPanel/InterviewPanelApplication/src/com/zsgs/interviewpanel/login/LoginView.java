package com.zsgs.interviewpanel.login;

import java.io.Console;
import java.util.Scanner;

import com.zsgs.interviewpanel.validator.Validator;

public class LoginView {
    LoginModel loginModel;
    Scanner scanner = new Scanner(System.in);
    Console console = System.console();

    public LoginView() {
        loginModel = new LoginModel(this);
    }

    public void init() {
        String userName;
        char[] password;
        String str;
        do {
            System.out.println("Welcome to the Login Page");
            do {
                System.out.print("Enter the user name: ");
                userName = scanner.next();
            } while (!Validator.validateName(userName));
            do {
                System.out.print("Enter the password: ");
                password = console.readPassword();
                str = new String(password);
            } while (!Validator.validatePassword(str));
        } while (!loginModel.validateUser(userName, str));
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
