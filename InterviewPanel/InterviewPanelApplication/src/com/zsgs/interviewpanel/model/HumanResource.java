package com.zsgs.interviewpanel.model;

public class HumanResource extends Employee {

    public HumanResource(String employeeName, String employeeEmail, String employeePhone, int credentialId) {
        super(employeeName, employeeEmail, employeePhone, credentialId, EmployeeType.HR);
    }
}
