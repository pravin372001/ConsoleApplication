package com.zsgs.librarymanagement.user;

import java.util.Scanner;

import com.zsgs.librarymanagement.model.User;
import com.zsgs.librarymanagement.validator.Validator;

import java.util.List;

public class UserView {
    UserModel userModel;
    Scanner scanner = new Scanner(System.in);

    public UserView() {
        userModel = new UserModel(this);
    }

    public void addNewUser() {
        String emailId;
        String phoneNumber;
        System.out.println("Add new user");
        System.out.print("Enter the user name : ");
        String userName = scanner.nextLine();
        do {
            System.out.print("Enter the valid emailId :");
            emailId = scanner.next();
        } while (!Validator.validateEmail(emailId));
        do {
            System.out.print("Enter the phone number: ");
            phoneNumber = scanner.next();
        } while (!Validator.validatePhoneNo(phoneNumber));
        scanner.nextLine();
        System.out.print("Enter the address : ");
        String address = scanner.nextLine();
        User user = new User(userName, phoneNumber, emailId, address);
        userModel.addNewUser(user);
    }

    public void showAllUsers() {
        List<User> userList = userModel.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    public void removeUserById() {
        System.out.print("Enter the user id : ");
        int userId = scanner.nextInt();
        userModel.removeUserById(userId);

    }

    public void showAlert(String string) {
        System.out.println(string);
    }

}
