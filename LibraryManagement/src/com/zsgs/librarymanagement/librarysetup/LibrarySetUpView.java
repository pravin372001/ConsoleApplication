package com.zsgs.librarymanagement.librarysetup;

import com.zsgs.librarymanagement.homepage.HomePage;
import com.zsgs.librarymanagement.model.Library;
import com.zsgs.librarymanagement.validator.Validator;

import java.util.Scanner;

public class LibrarySetUpView {
  private LibrarySetUpModel librarySetUpModel;

  Scanner scanner = new Scanner(System.in);

  public LibrarySetUpView() {
    librarySetUpModel = new LibrarySetUpModel(this);
  }

  public void init() {
    librarySetUpModel.startSetup();
  }

  public void onSetupComplete() {
    System.out.println("\nLibrary setup completed");
    HomePage.getInstance().init();
  }

  public void showAlert(String alert) {
    System.out.println("\n\n" + alert + "\n\n");
  }

  public void initiateSetup() {
    System.out.println("\n\nEnter the Library Details \n\n");
    System.out.print("Enter Library Id: ");
    int libraryId = scanner.nextInt();
    scanner.nextLine();
    System.out.print("\nEnter Library Name: ");
    String libraryName = scanner.nextLine();
    String phoneNo;
    do {
      System.out.print("Enter the valid Phone Number: ");
      phoneNo = scanner.next();
    } while (!Validator.validatePhoneNo(phoneNo));
    String emailId;
    do {
      System.out.print("Enter the valid Email Id: ");
      emailId = scanner.next();
    } while (!Validator.validateEmail(emailId));
    System.out.print("Enter Address: ");
    scanner.nextLine();
    String address = scanner.nextLine();
    Library library = new Library(libraryName, libraryId, phoneNo, emailId, address);
    librarySetUpModel.saveLibraryDetails(library);
    onSetupComplete();
  }
}
