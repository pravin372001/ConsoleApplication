package com.zsgs.interviewpanel.model;

public class Receptionist extends Employee {

    public Receptionist(String employeeName, String employeeEmail, String employeePhone, int credentialId,
            EmployeeType employeeType) {
        super(employeeName, employeeEmail, employeePhone, credentialId, EmployeeType.Receptionist);
    }
}
