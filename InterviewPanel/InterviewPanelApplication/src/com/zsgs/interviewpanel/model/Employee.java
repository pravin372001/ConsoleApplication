package com.zsgs.interviewpanel.model;

import com.zsgs.interviewpanel.datalayer.InterViewPanelDB;

public abstract class Employee {

    private int employeeId;
    private String employeeName;
    private String employeeEmail;
    private String employeePhone;
    private int credentialId;
    private EmployeeType employeeType;

    public Employee(String employeeName, String employeeEmail, String employeePhone, int credentialId,
            EmployeeType employeeType) {
        this.employeeId = InterViewPanelDB.getInstance().getEmployeeMap().size() + 1;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
        this.credentialId = credentialId;
        this.employeeType = employeeType;
    }

    public Employee(int credentialId, EmployeeType employeeType) {
        this.employeeId = 1;
        this.credentialId = credentialId;
        this.employeeType = employeeType;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public int getCredentialId() {
        return credentialId;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeEmail="
                + employeeEmail + ", employeePhone=" + employeePhone + ", credentialId=" + credentialId
                + ", employeeType=" + employeeType + "]";
    }
}
