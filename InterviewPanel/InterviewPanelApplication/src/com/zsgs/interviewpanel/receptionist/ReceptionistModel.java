package com.zsgs.interviewpanel.receptionist;

import java.util.Map;

import com.zsgs.interviewpanel.datalayer.InterViewPanelDB;
import com.zsgs.interviewpanel.home.HomePage;
import com.zsgs.interviewpanel.model.Credentials;
import com.zsgs.interviewpanel.model.Employee;
import com.zsgs.interviewpanel.model.EmployeeType;
import com.zsgs.interviewpanel.model.Receptionist;

public class ReceptionistModel {

    private ReceptionistView receptionistView;

    public ReceptionistModel(ReceptionistView receptionistView) {
        this.receptionistView = receptionistView;
    }

    public void addReceptionist(String receptionistName, String receptionistEmail, String receptionistPhone) {
        String name = InterViewPanelDB.getInstance().getCompany().getCompanyName();
        Credentials credentials = new Credentials(receptionistName, name + "Recep@123");
        int credentialId = InterViewPanelDB.getInstance().addCredential(credentials);
        Receptionist receptionist = new Receptionist(receptionistName, receptionistEmail, receptionistPhone,
                credentialId, EmployeeType.Receptionist);
        InterViewPanelDB.getInstance().addEmployee(receptionist.getEmployeeId(), receptionist);
        receptionistView.showMessage("Receptionist added successfully");
        serializeReceptionist();
    }

    private void serializeReceptionist() {
        InterViewPanelDB.getInstance().serializeEmployee(InterViewPanelDB.getInstance().getEmployeeMap());
        InterViewPanelDB.getInstance().serializeCredentialList(InterViewPanelDB.getInstance().getCredentialList());
    }

    public Map<Integer, Employee> getAllEmployees(EmployeeType receptionist) {
        return InterViewPanelDB.getInstance().getSpecificEmployees(receptionist);
    }

    public void removeReceptionist(int receptionistId) {
        InterViewPanelDB.getInstance().removeEmployee(receptionistId);
    }

    public void updateReceptionistPassword(String password, Credentials credentials) {
        if (InterViewPanelDB.getInstance().updateEmployeePassword(password, credentials)) {
            receptionistView.showMessage("Password updated successfully");
            HomePage.getInstance().receptionistView();
        } else {
            receptionistView.showMessage("Password not updated successfully");
        }
    }

    public boolean isDefaultPassword(Credentials credentials) {
        String name = InterViewPanelDB.getInstance().getCompany().getCompanyName();
        return credentials.getPassword().equals(name + "Recep@123");
    }
}
