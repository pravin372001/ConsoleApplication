package com.zsgs.librarymanagement.admin;

import java.io.Console;
import java.util.Scanner;

import com.zsgs.librarymanagement.homepage.HomePage;
import com.zsgs.librarymanagement.librarysetup.LibrarySetUpView;
import com.zsgs.librarymanagement.validator.Validator;

public class AdminView {
    Scanner scanner = new Scanner(System.in);
    Console console = System.console();
    AdminModel adminModel;

    public AdminView() {
        adminModel = new AdminModel(this);
    }

    public void initiateSetup() {
        if (adminModel.isDefaultPassword()) {
            String password;
            String emailId;
            String phoneNumber;
            System.out.println("Initiate Setup");
            do {
                System.out.println(
                        "Password must contains atleast 8 character with one caps, one special character and numbers");
                System.out.print("Enter the new password :");
                password = new String(console.readPassword());
            } while (!Validator.validatePassword(password));
            do {
                System.out.print("Enter the valid email id : ");
                emailId = scanner.next();
            } while (!Validator.validateEmail(emailId));
            do {
                System.out.print("Enter the valid phone number : ");
                phoneNumber = scanner.next();
            } while (!Validator.validatePhoneNo(phoneNumber));
            adminModel.initiateSetup(password, emailId, phoneNumber);
        } else {
            HomePage.getInstance().init();
        }
    }

    public void onSetupComplete() {
        System.out.println("Setup completed successfully...");
        LibrarySetUpView librarySetupView = new LibrarySetUpView();
        librarySetupView.init();
    }

    public void showAlert(String string) {
        System.out.println(string);
    }
}
