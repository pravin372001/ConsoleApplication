package com.zsgs.interviewpanel.home;

import java.util.Scanner;

import com.zsgs.interviewpanel.admin.AdminView;
import com.zsgs.interviewpanel.candidate.CandidateView;
import com.zsgs.interviewpanel.hr.HumanResourceView;
import com.zsgs.interviewpanel.login.LoginView;
import com.zsgs.interviewpanel.receptionist.ReceptionistView;

public class HomePage {

    private static HomePage homePage;
    static Scanner scanner = new Scanner(System.in);

    private HomePage() {

    }

    public static HomePage getInstance() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public void adminView() {
        while (true) {
            try {
                System.out.println("Welcome to the Interview Panel");
                System.out.println("┌──────────────────────────────┐");
                System.out.println("│       Admin Menu             │");
                System.out.println("├──────────────────────────────┤");
                System.out.println("│ 1. Add Receptionist          │");
                System.out.println("│ 2. Remove Receptionist       │");
                System.out.println("│ 3. Add HR                    │");
                System.out.println("│ 4. Remove HR                 │");
                System.out.println("│ 5. Show Employees            │");
                System.out.println("│ 6. Logout                    │");
                System.out.println("│ 7. Exit                      │");
                System.out.println("└──────────────────────────────┘");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        new ReceptionistView().addReceptionist();
                        break;

                    case 2:
                        new ReceptionistView().removeReceptionist();
                        break;

                    case 3:
                        new HumanResourceView().addHumanResource();
                        break;

                    case 4:
                        new HumanResourceView().removeHumanResource();
                        break;

                    case 5:
                        new AdminView().showEmployees();
                        break;

                    case 6:
                        System.out.println("Thank you for using the panel");
                        new LoginView().init();

                    case 7:
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice");
                scanner.next();
            }

        }
    }

    public void receptionistView() {
        while (true) {
            try {
                System.out.println("┌──────────────────────────────┐");
                System.out.println("│         Welcome Receptionist │");
                System.out.println("├──────────────────────────────┤");
                System.out.println("│ 1. Add Candidate             │");
                System.out.println("│ 2. Show All Candidates       │");
                System.out.println("│ 3. Remove Candidate          │");
                System.out.println("│ 4. Logout                    │");
                System.out.println("└──────────────────────────────┘");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        new CandidateView().addCandidate();
                        break;

                    case 2:
                        new CandidateView().showAllCandidate();
                        break;

                    case 3:
                        new CandidateView().removeCandidate();

                    case 4:
                        System.out.println("Thanks for using InterviewPanel");
                        new LoginView().init();
                        break;

                }
            } catch (Exception e) {
                System.out.println("Invalid choice");
                scanner.next();
            }

        }

    }

    public void humanResourcesView() {
        while (true) {
            try {
                System.out.println("Welcome Human Resources");
                System.out.println("┌──────────────────────────────┐");
                System.out.println("│        HR Menu               │");
                System.out.println("├──────────────────────────────┤");
                System.out.println("│ 1. Interview Process         │");
                System.out.println("│ 2. Remaining Candidates      │");
                System.out.println("│ 3. Selected Candidate        │");
                System.out.println("│ 4. Logout                    │");
                System.out.println("└──────────────────────────────┘");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        new HumanResourceView().interviewProcess();
                        break;

                    case 2:
                        new HumanResourceView().remainingCandidates();
                        break;

                    case 3:
                        new HumanResourceView().selectedCandidates();
                        break;

                    case 4:
                        System.out.println("Thanks for using InterviewPanel");
                        new LoginView().init();
                        break;

                    default:
                        System.out.println("Please enter the valid choice");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter the valid choice");
                scanner.next();
            }

        }
    }

}
