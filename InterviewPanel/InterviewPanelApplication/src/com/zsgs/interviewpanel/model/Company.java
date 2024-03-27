package com.zsgs.interviewpanel.model;

public class Company {

    private int companyId;
    private String companyName;
    private String companyEmail;
    private String companyMobile;
    private String companyAddress;
    private static int companyIdCounter = 1;

    public Company(String companyName, String companyEmail, String companyMobile, String companyAddress) {
        this.companyId = companyIdCounter++;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyMobile = companyMobile;
        this.companyAddress = companyAddress;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

}
