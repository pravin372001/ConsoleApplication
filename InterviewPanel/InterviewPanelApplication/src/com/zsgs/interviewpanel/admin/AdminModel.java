package com.zsgs.interviewpanel.admin;

import java.util.Map;

import com.zsgs.interviewpanel.companysetup.CompanySetupView;
import com.zsgs.interviewpanel.datalayer.InterViewPanelDB;
import com.zsgs.interviewpanel.home.HomePage;
import com.zsgs.interviewpanel.model.Admin;
import com.zsgs.interviewpanel.model.Credentials;
import com.zsgs.interviewpanel.model.Employee;

public class AdminModel {
    private AdminView adminView;

    public AdminModel(AdminView adminView) {
        this.adminView = adminView;
    }

    public boolean isDefaultPassword() {
        int credentialId = InterViewPanelDB.getInstance().getAdmin().getCredentialId();
        return InterViewPanelDB.getInstance().getCredentials(credentialId).getPassword().equals("Admin@123");
    }

    public void initiateSetup(String name, String password, String emailId) {
        if (isDefaultPassword()) {
            Admin admin = InterViewPanelDB.getInstance().getAdmin();
            admin.setEmployeeEmail(emailId);
            admin.setEmployeeName(name);
            Credentials credentials = new Credentials(name, password);
            InterViewPanelDB.getInstance().initiateSetup(admin, credentials);
            adminView.showMessage("Admin Setup completed successfully...");
            serialize(admin);
            new CompanySetupView().init();
        } else {
            HomePage.getInstance().adminView();
        }
    }

    private void serialize(Admin admin) {
        InterViewPanelDB.getInstance().serializeAdmin(admin);
        InterViewPanelDB.getInstance().serializeCredentialList(InterViewPanelDB.getInstance().getCredentialList());
    }

    public Map<Integer, Employee> getAllEmployees() {
        return InterViewPanelDB.getInstance().getEmployeeMap();
    }

}
