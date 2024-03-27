package com.zsgs.interviewpanel.candidate;

import java.util.Map;
import java.util.Queue;

import com.zsgs.interviewpanel.datalayer.InterViewPanelDB;
import com.zsgs.interviewpanel.model.Candidate;
import com.zsgs.interviewpanel.model.Employee;
import com.zsgs.interviewpanel.model.EmployeeType;

public class CandidateModel {
    CandidateView candidateView;

    public CandidateModel(CandidateView candidateView) {
        this.candidateView = candidateView;
    }

    public void addNewCandidate(int hrId, Candidate candidate) {
        InterViewPanelDB.getInstance().addCandidate(hrId, candidate);
        InterViewPanelDB.getInstance().serializeInterviewMap(InterViewPanelDB.getInstance().getInterviewMap());
        candidateView.showMessage("Candidate added successfully");
    }

    public Map<Integer, Employee> getAllHR() {
        return InterViewPanelDB.getInstance().getSpecificEmployees(EmployeeType.HR);
    }

    public void isAlreadyQueue(int hrId) {

        if (InterViewPanelDB.getInstance().getCandidateList(hrId) == null) {
            InterViewPanelDB.getInstance().addInterViewPanel(hrId);
        }
    }

    public Queue<Candidate> getCandidateList(int hrId) {
        return InterViewPanelDB.getInstance().getCandidateList(hrId);
    }

    public void removeCandidate(int hrId) {
        InterViewPanelDB.getInstance();
    }
}
