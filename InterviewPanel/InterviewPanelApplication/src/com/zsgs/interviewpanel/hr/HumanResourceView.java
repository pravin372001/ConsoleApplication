package com.zsgs.interviewpanel.hr;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import com.zsgs.interviewpanel.home.HomePage;
import com.zsgs.interviewpanel.model.Candidate;
import com.zsgs.interviewpanel.model.Credentials;
import com.zsgs.interviewpanel.model.Employee;
import com.zsgs.interviewpanel.model.EmployeeType;
import com.zsgs.interviewpanel.validator.Validator;

public class HumanResourceView {
    private HumanResourceModel humanResourceModel;
    static Scanner scanner = new Scanner(System.in);

    public HumanResourceView() {
        this.humanResourceModel = new HumanResourceModel(this);
    }

    public void addHumanResource() {
        String name;
        String email;
        String phone;
        System.out.println("Add New Human Resource");
        do {
            System.out.print("Enter the name : ");
            name = scanner.next();
        } while (!Validator.validateName(name));
        do {
            System.out.print("Enter the email id : ");
            email = scanner.next();
        } while (!Validator.validateEmail(email));
        do {
            System.out.print("Enter the phone number : ");
            phone = scanner.next();
        } while (!Validator.validatePhoneNo(phone));
        humanResourceModel.addHumanResource(name, email, phone);
    }

    public void showMessage(String string) {
        System.out.println(string);
    }

    public void removeHumanResource() {
        System.out.println("\nRemove Human Resource\n");
        showAllEmployees();
        System.out.print("Enter the Employee Id: ");
        int employeeId = scanner.nextInt();
        humanResourceModel.removeEmployee(employeeId);
    }

    private void showAllEmployees() {
        Map<Integer, Employee> employeeMap = humanResourceModel.getAllEmployees(EmployeeType.HR);
        employeeMap.forEach((k, v) -> System.out.println(k + " " + v));
    }

    public void init(Credentials credentials) {
        if (humanResourceModel.isDefaultPassword(credentials)) {
            updatePassword(credentials);
        } else {
            HomePage.getInstance().humanResourcesView();
        }
    }

    private void updatePassword(Credentials credentials) {
        String password;
        do {
            System.out.println("\nUpdate Receptionist Password");
            System.out.print("Enter the Receptionist Password: ");
            password = scanner.next();
        } while (!Validator.validatePassword(password));
        humanResourceModel.updatePassword(password, credentials);
    }

    public void interviewProcess() {
        System.out.println("\nInterview Process\n");
        do {
            System.out.println("1.add next candidate to panel ");
            System.out.println("2.remove candidate from panel ");
            System.out.println("3.back to home page ");
            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                humanResourceModel.addCandidateFromPanel();
            } else if (choice == 2) {
                humanResourceModel.removeCandidateFromPanel();
            } else if (choice == 3) {
                return;
            }
        } while (true);

    }

    public int getInput() {
        System.out.println("1. Selected Candidate");
        System.out.println("2.Not Selected Candidate");
        System.out.print("Enter your choice : ");
        return scanner.nextInt();
    }

    public void selectedCandidates() {
        List<Candidate> selectedCandidates = humanResourceModel.getSelectedCandidates();
        if (selectedCandidates.isEmpty()) {
            System.out.println("No candidate selected");
            return;
        }
        for (Candidate candidate : selectedCandidates) {
            System.out.print("Candidate Name : " + candidate.getCandidateName() + "\n" + "Candidate Email : "
                    + candidate.getCandidateEmail() + "\n" + "Candidate Phone : " + candidate.getCandidateMobile());
        }
    }

    public void remainingCandidates() {
        Queue<Candidate> selectedCandidates = humanResourceModel.getRemainingCandidates();
        if (selectedCandidates.isEmpty()) {
            System.out.println("No candidate selected");
            return;
        }
        for (Candidate candidate : selectedCandidates) {
            System.out.print("Candidate Name : " + candidate.getCandidateName() + "\n" + "Candidate Email : "
                    + candidate.getCandidateEmail() + "\n" + "Candidate Phone : " + candidate.getCandidateMobile());
        }
    }
}
