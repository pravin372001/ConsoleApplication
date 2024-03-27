package com.zsgs.librarymanagement.model;

import com.zsgs.librarymanagement.Librarydatabase.LibraryDatabase;

public class User {
    private String name;
    private int id;
    private String phoneNo;
    private String emailId;
    private String address;
    private double fine;

    public User(String name, String phoneNo, String emailId, String address) {
        this.name = name;
        this.id = LibraryDatabase.getInstance().getUserList().size() + 1;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        this.address = address;
        this.fine = 0.0;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", phoneNo='" + phoneNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", address='" + address + '\'' +
                ", fine=" + fine +
                '}';
    }
}
