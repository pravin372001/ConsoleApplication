package com.zsgs.interviewpanel.receptionist;

import java.util.Map;
import java.util.Scanner;

import com.zsgs.interviewpanel.home.HomePage;
import com.zsgs.interviewpanel.model.Credentials;
import com.zsgs.interviewpanel.model.Employee;
import com.zsgs.interviewpanel.model.EmployeeType;
import com.zsgs.interviewpanel.validator.Validator;

public class ReceptionistView {

    private ReceptionistModel receptionistModel;
    static Scanner scanner = new Scanner(System.in);

    public ReceptionistView() {
        this.receptionistModel = new ReceptionistModel(this);
    }

    public void addReceptionist() {
        String receptionistName;
        String receptionistEmail;
        String receptionistPhone;
        System.out.println("Add Receptionist");
        do {
            System.out.print("Enter the Receptionist name: ");
            receptionistName = scanner.next();
        } while (!Validator.validateName(receptionistName));
        do {
            System.out.print("Enter the Receptionist email: ");
            receptionistEmail = scanner.next();
        } while (!Validator.validateEmail(receptionistEmail));
        do {
            System.out.print("Enter the Receptionist phone: ");
            receptionistPhone = scanner.next();
        } while (!Validator.validatePhoneNo(receptionistPhone));
        receptionistModel.addReceptionist(receptionistName, receptionistEmail, receptionistPhone);

    }

    public void showMessage(String string) {
        System.out.println(string);
    }

    public void removeReceptionist() {
        System.out.println("\nRemove Receptionist\n");
        showAllReceptionists();
        System.out.print("Enter the Receptionist Id: ");
        int receptionistId = scanner.nextInt();
        receptionistModel.removeReceptionist(receptionistId);
    }

    private void updateReceptionistPassword(Credentials credentials) {
        String password;
        do {
            System.out.println("\nUpdate Receptionist Password");
            System.out.print("Enter the Receptionist Password: ");
            password = scanner.next();
        } while (!Validator.validatePassword(password));
        receptionistModel.updateReceptionistPassword(password, credentials);
    }

    private void showAllReceptionists() {
        Map<Integer, Employee> receptionistMap = receptionistModel.getAllEmployees(EmployeeType.Receptionist);
        receptionistMap.forEach((k, v) -> System.out.println(k + " " + v));
    }

    public void init(Credentials credentials) {
        if (receptionistModel.isDefaultPassword(credentials)) {
            updateReceptionistPassword(credentials);
        } else {
            HomePage.getInstance().receptionistView();
        }
    }
}
