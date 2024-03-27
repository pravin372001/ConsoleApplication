package com.zsgs.interviewpanel.companysetup;

import java.util.Scanner;

import com.zsgs.interviewpanel.model.Company;
import com.zsgs.interviewpanel.validator.Validator;

public class CompanySetupView {

    private CompanySetupModel companyStepModel;
    static Scanner scanner = new Scanner(System.in);

    public CompanySetupView() {
        this.companyStepModel = new CompanySetupModel(this);
    }

    public void init() {
        String companyEmail;
        String companyMobile;
        System.out.println("Welcome to the Company Step Up");
        System.out.println("Enter the name of the company :");
        String companyName = scanner.next();
        do {
            System.out.println("Enter the email id of the company:");
            companyEmail = scanner.next();
        } while (!Validator.validateEmail(companyEmail));

        do {
            System.out.println("Enter the mobile number of the company:");
            companyMobile = scanner.next();
        } while (!Validator.validatePhoneNo(companyMobile));
        System.out.println("Enter the address of the company :");
        String companyAddress = scanner.next();
        Company company = new Company(companyName, companyEmail, companyMobile, companyAddress);
        companyStepModel.initiateSetup(company);
    }

    public void showMessage(String string) {
        System.out.println(string);
    }

}
