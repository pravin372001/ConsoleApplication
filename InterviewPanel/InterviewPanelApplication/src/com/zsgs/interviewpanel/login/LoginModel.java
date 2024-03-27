package com.zsgs.interviewpanel.login;

import java.util.List;

import com.zsgs.interviewpanel.admin.AdminView;
import com.zsgs.interviewpanel.datalayer.InterViewPanelDB;
import com.zsgs.interviewpanel.hr.HumanResourceView;
import com.zsgs.interviewpanel.model.Admin;
import com.zsgs.interviewpanel.model.Credentials;
import com.zsgs.interviewpanel.model.Employee;
import com.zsgs.interviewpanel.model.EmployeeType;
import com.zsgs.interviewpanel.receptionist.ReceptionistView;

public class LoginModel {
    LoginView loginView;
    Admin admin = InterViewPanelDB.getInstance().getAdmin();

    public LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public boolean validateUser(String userName, String password) {
        List<Credentials> credentialList = InterViewPanelDB.getInstance().getCredentialList();
        int j = 0;
        for (int i = 0; i < credentialList.size(); i++) {
            Credentials credentials = credentialList.get(i);
            if (credentials.getUsername().equals(userName)) {
                if (credentials.getPassword().equals(password)) {
                    Employee employee = InterViewPanelDB.getInstance().getEmployeeType(credentials.getCredentialId());
                    identitfyLogin(employee, credentials);
                    return true;
                } else {
                    j = 1;
                }
            } else {
                j = -1;
            }
        }
        if (j == 1) {
            loginView.showMessage("Invalid Password");
        } else if (j == -1) {
            loginView.showMessage("Invalid User Name");
        }
        return false;
    }

    private void identitfyLogin(Employee employee, Credentials credentials) {
        EmployeeType employeeType = employee.getEmployeeType();
        int employeeId = employee.getEmployeeId();
        if (employeeType.equals(EmployeeType.Admin)) {
            loginView.showMessage("Login as Admin");
            InterViewPanelDB.getInstance().setEmployeeId(employeeId);
            new AdminView().initiateSetup();
        } else if (employeeType.equals(EmployeeType.Receptionist)) {
            loginView.showMessage("Login as Receptionist");
            InterViewPanelDB.getInstance().setEmployeeId(employeeId);
            new ReceptionistView().init(credentials);
        } else if (employeeType.equals(EmployeeType.HR)) {
            loginView.showMessage("Login as HR");
            InterViewPanelDB.getInstance().setEmployeeId(employeeId);
            new HumanResourceView().init(credentials);
        }
    }
}
