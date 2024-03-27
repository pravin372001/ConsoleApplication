package com.zsgs.interviewpanel.admin;

import com.zsgs.interviewpanel.home.HomePage;
import com.zsgs.interviewpanel.validator.Validator;
import com.zsgs.interviewpanel.model.Employee;

import java.util.Scanner;
import java.util.Map;

public class AdminView {
    AdminModel adminModel;
    Scanner scanner = new Scanner(System.in);

    public AdminView() {
        adminModel = new AdminModel(this);
    }

    public void initiateSetup() {
        if (adminModel.isDefaultPassword()) {
            String password;
            String emailId;
            String name;
            System.out.println("Admin Setup");
            do {
                System.out.print("Enter the name : ");
                name = scanner.next();
            } while (!Validator.validateName(name));
            do {
                System.out.print("Enter the new password : ");
                password = scanner.next();
            } while (!Validator.validatePassword(password));
            do {
                System.out.print("Enter the email id : ");
                emailId = scanner.next();
            } while (!Validator.validateEmail(emailId));
            adminModel.initiateSetup(name, password, emailId);
        } else {
            HomePage.getInstance().adminView();
        }
    }

    public void onSetupComplete() {
        System.out.println("Setup completed successfully...");
        HomePage.getInstance().adminView();
    }

    public void showMessage(String string) {
        System.out.println(string);
    }

    public void showEmployees() {
        Map<Integer, Employee> employeeMap = adminModel.getAllEmployees();
        employeeMap.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
