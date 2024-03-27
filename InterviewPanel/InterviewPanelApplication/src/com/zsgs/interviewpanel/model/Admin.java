package com.zsgs.interviewpanel.model;

public class Admin extends Employee {

    public Admin(String employeeName, String employeeEmail, String employeePhone, int credentialId,
            EmployeeType employeeType) {
        super(employeeName, employeeEmail, employeePhone, credentialId, EmployeeType.Admin);
    }

    public Admin(int credentialId, EmployeeType employeeType) {
        super(credentialId, EmployeeType.Admin);
    }

}
