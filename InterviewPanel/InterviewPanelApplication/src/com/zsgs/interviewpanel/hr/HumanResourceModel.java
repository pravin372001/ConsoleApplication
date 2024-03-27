package com.zsgs.interviewpanel.hr;

import java.util.Map;

import com.zsgs.interviewpanel.datalayer.InterViewPanelDB;
import com.zsgs.interviewpanel.home.HomePage;
import com.zsgs.interviewpanel.model.Candidate;
import com.zsgs.interviewpanel.model.Company;
import com.zsgs.interviewpanel.model.Credentials;
import com.zsgs.interviewpanel.model.Employee;
import com.zsgs.interviewpanel.model.EmployeeType;
import com.zsgs.interviewpanel.model.HumanResource;

import java.util.List;
import java.util.Queue;

public class HumanResourceModel {

    private HumanResourceView humanResourceView;

    public HumanResourceModel(HumanResourceView humanResourceView) {
        this.humanResourceView = humanResourceView;
    }

    public void addEmployee(Employee employee) {

    }

    public void addHumanResource(String name, String email, String phone) {
        String CompanyName = InterViewPanelDB.getInstance().getCompany().getCompanyName();
        Credentials credentials = new Credentials(name, CompanyName + "Hr@123");
        int credentialId = InterViewPanelDB.getInstance().addCredential(credentials);
        Employee humanResource = new HumanResource(name, email, phone, credentialId);
        InterViewPanelDB.getInstance().addEmployee(humanResource.getEmployeeId(), humanResource);
        humanResourceView.showMessage("Human Resource added successfully");
        serializeHumanResource();
    }

    private void serializeHumanResource() {
        InterViewPanelDB.getInstance().serializeEmployee(InterViewPanelDB.getInstance().getEmployeeMap());
        InterViewPanelDB.getInstance().serializeCredentialList(InterViewPanelDB.getInstance().getCredentialList());
    }

    public void removeEmployee(int employeeId) {
        InterViewPanelDB.getInstance().removeEmployee(employeeId);
    }

    public Map<Integer, Employee> getAllEmployees(EmployeeType hr) {
        return InterViewPanelDB.getInstance().getSpecificEmployees(hr);
    }

    public boolean isDefaultPassword(Credentials credentials) {
        Company company = InterViewPanelDB.getInstance().getCompany();
        return credentials.getPassword().equals(company.getCompanyName() + "Hr@123");
    }

    public void updatePassword(String password, Credentials credentials) {
        if (InterViewPanelDB.getInstance().updateEmployeePassword(password, credentials)) {
            humanResourceView.showMessage("Password updated successfully");
            HomePage.getInstance().humanResourcesView();
        } else {
            humanResourceView.showMessage("Password not updated ");
        }
    }

    public void addCandidateFromPanel() {
        Candidate candidate = InterViewPanelDB.getInstance().getCandidate();
        int hrId = InterViewPanelDB.getInstance().getEmployeeId();
        Queue<Candidate> queue = InterViewPanelDB.getInstance().getInterviewMap().get(hrId);
        if (candidate == null) {
            candidate = queue.poll();
            InterViewPanelDB.getInstance().setCandidate(candidate);
            humanResourceView.showMessage("Candidate " + candidate.getCandidateName() + " is added to panel");
        } else {
            humanResourceView.showMessage("Candidate " + candidate.getCandidateName() + " is already in panel");
        }
    }

    public void removeCandidateFromPanel() {
        Candidate candidate = InterViewPanelDB.getInstance().getCandidate();
        if (candidate == null) {
            humanResourceView.showMessage("No candidate in panel");
        } else {
            humanResourceView.showMessage("Candidate " + candidate.getCandidateName() + "is removed from panel");
            int input = humanResourceView.getInput();
            if (input == 1) {
                if (getSelectedCandidates() == null) {
                    InterViewPanelDB.getInstance()
                            .addSelectedCandidates(InterViewPanelDB.getInstance().getEmployeeId());
                }
                InterViewPanelDB.getInstance().addSelectedCandidates(InterViewPanelDB.getInstance().getEmployeeId(),
                        candidate);
                serializeSelectedCandidates();
            }
            InterViewPanelDB.getInstance().removeCandidateFromPanel(candidate);
            InterViewPanelDB.getInstance().setCandidate(null);
        }
    }

    public List<Candidate> getSelectedCandidates() {
        return InterViewPanelDB.getInstance().getSelectedCandidates(InterViewPanelDB.getInstance().getEmployeeId());
    }

    private void serializeSelectedCandidates() {
        InterViewPanelDB.getInstance()
                .serializeSelectedCandidate(InterViewPanelDB.getInstance().getSelectedCandidate());
    }

    public Queue<Candidate> getRemainingCandidates() {
        return InterViewPanelDB.getInstance().getRemainingCandidates(InterViewPanelDB.getInstance().getEmployeeId());
    }

}
