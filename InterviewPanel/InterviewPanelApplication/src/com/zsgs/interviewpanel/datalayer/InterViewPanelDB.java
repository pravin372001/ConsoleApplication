package com.zsgs.interviewpanel.datalayer;

import com.google.gson.reflect.TypeToken;
import com.zsgs.interviewpanel.model.Admin;
import com.zsgs.interviewpanel.model.Candidate;
import com.zsgs.interviewpanel.model.Company;
import com.zsgs.interviewpanel.model.Credentials;
import com.zsgs.interviewpanel.model.Employee;
import com.zsgs.interviewpanel.model.EmployeeType;
import com.zsgs.interviewpanel.serialize.JsonSerialize;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.ArrayDeque;

public class InterViewPanelDB {
    private static InterViewPanelDB interViewPanelDB;
    private Admin admin;
    private int currentEmployeeId;
    private Company company;
    private Candidate candidate;
    private List<Credentials> credentialList = new ArrayList<>();
    private Map<Integer, Queue<Candidate>> interviewMap = new HashMap<>();
    private Map<Integer, List<Candidate>> selectedCandidate = new HashMap<>();
    private Map<Integer, Employee> employeeMap = new HashMap<>();

    private InterViewPanelDB() {

    }

    public void setEmployeeId(int employeeId) {
        currentEmployeeId = employeeId;
    }

    public int getEmployeeId() {
        return currentEmployeeId;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public static InterViewPanelDB getInstance() {
        if (interViewPanelDB == null) {
            interViewPanelDB = new InterViewPanelDB();
        }
        return interViewPanelDB;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Company getCompany() {
        return company;
    }

    public Map<Integer, Queue<Candidate>> getInterviewMap() {
        return interviewMap;
    }

    public void addInterViewPanel(Integer integer) {
        interviewMap.put(integer, new ArrayDeque<Candidate>());
    }

    public void addCandidate(Integer interviewPanelId, Candidate candidate) {
        interviewMap.get(interviewPanelId).add(candidate);
    }

    public void removeCandidate(Integer interviewPanelId, Candidate candidate) {
        interviewMap.get(interviewPanelId).remove(candidate);
    }

    public Queue<Candidate> getCandidateList(Integer interviewPanelId) {
        return interviewMap.get(interviewPanelId);
    }

    public void addEmployee(Integer employeeId, Employee employee) {
        employeeMap.put(employeeId, employee);
    }

    public Map<Integer, Employee> getEmployeeMap() {
        return employeeMap;
    }

    public void addSelectedCandidates(Integer hrId) {
        selectedCandidate.put(hrId, new ArrayList<>());
    }

    public void addSelectedCandidates(Integer interviewPanelId, Candidate candidate) {
        selectedCandidate.get(interviewPanelId).add(candidate);
    }

    public Credentials getCredentials(int credentialId) {
        return credentialList.get(credentialId - 1);
    }

    public void initiateSetup(Admin admin2, Credentials credentials) {
        credentialList.clear();
        credentials.setCredentialId(1);
        credentialList.add(credentials);
        admin = admin2;
    }

    public void addCompany(Company company) {
        this.company = company;
    }

    public Employee getEmployeeType(int credentialId) {
        if (credentialId == 1) {
            return admin;
        }
        for (Employee employee : employeeMap.values()) {
            if (employee.getCredentialId() == credentialId) {
                return employee;
            }
        }
        return null;
    }

    public Map<Integer, Employee> getSpecificEmployees(EmployeeType employeeType) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employeeMap.values()) {
            if (employee.getEmployeeType() == employeeType) {
                employeeMap.put(employee.getEmployeeId(), employee);
            }
        }
        return employeeMap;
    }

    public void removeEmployee(int employeeId) {
        employeeMap.remove(employeeId);
    }

    public List<Credentials> getCredentialList() {
        return credentialList;
    }

    public int addCredential(Credentials credentials) {
        credentialList.add(credentials);
        return credentialList.size();
    }

    public boolean updateEmployeePassword(String password, Credentials credential) {
        Iterator<Credentials> credentialsIterator = credentialList.iterator();
        while (credentialsIterator.hasNext()) {
            Credentials curCredentials = credentialsIterator.next();
            if (curCredentials.getCredentialId() == credential.getCredentialId()) {
                curCredentials.setPassword(password);
                serializeCredentialList(credentialList);
                return true;
            }
        }
        return false;
    }

    public void removeCandidateFromPanel(Candidate candidate2) {
        Iterator<Candidate> candidateIterator = getCandidateList(candidate2.getHrId()).iterator();
        while (candidateIterator.hasNext()) {
            Candidate curCandidate = candidateIterator.next();
            if (curCandidate.getHrId() == candidate2.getHrId() && curCandidate.getCandidateId() == candidate2
                    .getCandidateId()) {
                candidateIterator.remove();
                serializeInterviewMap(interviewMap);
            }
        }
    }

    // Serialization saving the state of the object into json file

    public void serializeEmployee(Map<Integer, Employee> employeeMap) {
        JsonSerialize.getJsonSerialize().serialize(employeeMap,
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\employee.json");
    }

    public void serializeAdmin(Admin admin2) {
        JsonSerialize.getJsonSerialize().serialize(admin2,
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\admin.json");
    }

    public void serializeCompany(Object company2) {
        JsonSerialize.getJsonSerialize().serialize(company2,
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\company.json");
    }

    public void serializeCredentialList(List<Credentials> credentialList) {
        JsonSerialize.getJsonSerialize().serialize(credentialList,
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\credentialList.json");
    }

    public void serializeInterviewMap(Map<Integer, Queue<Candidate>> interviewMap) {
        JsonSerialize.getJsonSerialize().serialize(interviewMap,
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\interviewMap.json");
    }

    public void serializeSelectedCandidate(Map<Integer, List<Candidate>> selectedCandidate) {
        JsonSerialize.getJsonSerialize().serialize(selectedCandidate,
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\selectedCandidate.json");
    }

    // Deserialization retrieving the state of the object from json file

    private void deserializeEmployee() {
        Map<Integer, Employee> employeeMap = JsonSerialize.getJsonSerialize().deserialize(
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\employee.json");
        this.employeeMap = employeeMap;
    }

    private void deserializeAdmin() {
        Admin admin = JsonSerialize.getJsonSerialize().deserialize(
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\admin.json",
                Admin.class);
        this.admin = admin;
    }

    private void deserializeCompany() {
        Company company = JsonSerialize.getJsonSerialize().deserialize(
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\company.json",
                Company.class);
        this.company = company;
    }

    private void deserializeCredentialList() {
        List<Credentials> credentialList = JsonSerialize.getJsonSerialize().deserialize(
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\credentialList.json",
                new TypeToken<List<Credentials>>() {
                });
        this.credentialList = credentialList;
    }

    private void deserializeInterviewMap() {
        Map<Integer, Queue<Candidate>> interviewMap = JsonSerialize.getJsonSerialize().deserialize(
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\interviewMap.json",
                new TypeToken<Map<Integer, Queue<Candidate>>>() {
                });
        this.interviewMap = interviewMap;
    }

    private void deserializeSelectedCandidate() {
        Map<Integer, List<Candidate>> selectedCandidate = JsonSerialize.getJsonSerialize().deserialize(
                "InterviewPanelApplication\\src\\com\\zsgs\\interviewpanel\\data\\selectedCandidate.json",
                new TypeToken<Map<Integer, List<Candidate>>>() {
                });
        this.selectedCandidate = selectedCandidate;
    }

    public void deserializeFromDb() {
        deserializeEmployee();
        deserializeAdmin();
        deserializeCompany();
        deserializeCredentialList();
        deserializeInterviewMap();
        deserializeSelectedCandidate();
        if (this.credentialList.size() == 0 && this.employeeMap.size() == 0) {
            Credentials credentials = new Credentials("Admin", "Admin@123");
            admin = new Admin(credentials.getCredentialId(), EmployeeType.Admin);
            employeeMap.put(1, admin);
            credentialList.add(credentials);
        }
    }

    public List<Candidate> getSelectedCandidates(int employeeId) {
        return selectedCandidate.get(employeeId);
    }

    public Map<Integer, List<Candidate>> getSelectedCandidate() {
        return selectedCandidate;
    }

    public Queue<Candidate> getRemainingCandidates(int employeeId) {
        return interviewMap.get(employeeId);
    }

}
