package com.zsgs.interviewpanel.candidate;

import java.util.Scanner;
import java.util.Map;
import java.util.Queue;

import com.zsgs.interviewpanel.model.Candidate;
import com.zsgs.interviewpanel.model.Employee;
import com.zsgs.interviewpanel.validator.Validator;

public class CandidateView {
    private CandidateModel candidateModel;
    Scanner scanner = new Scanner(System.in);

    public CandidateView() {
        candidateModel = new CandidateModel(this);
    }

    public void addCandidate() {
        String candidateName;
        String candidateEmail;
        String candidatePhone;
        String candidateQualification;
        System.out.println("Add candidate ");
        do {
            System.out.print("candidate name: ");
            candidateName = scanner.nextLine();
        } while (!Validator.validateName(candidateName));
        do {
            System.out.print("candidate email: ");
            candidateEmail = scanner.next();
        } while (!Validator.validateEmail(candidateEmail));
        do {
            System.out.print("candidate phone :");
            candidatePhone = scanner.next();
        } while (!Validator.validatePhoneNo(candidatePhone));
        System.out.print("candidate Qualication :");
        candidateQualification = scanner.next();
        printHR();
        System.out.print("Enter the Hr Id :");
        int hrId = scanner.nextInt();
        candidateModel.isAlreadyQueue(hrId);
        Candidate candidate = new Candidate(candidateName, candidateEmail, candidatePhone,
                candidateQualification, hrId);
        candidateModel.addNewCandidate(hrId, candidate);

    }

    private void printHR() {
        Map<Integer, Employee> hrs = candidateModel.getAllHR();
        for (Map.Entry<Integer, Employee> e : hrs.entrySet()) {
            System.out.print("Hr Id : " + e.getKey() + " Hr Name : " + e.getValue().getEmployeeName());
        }
    }

    private void printCandidates() {
        printHR();
        System.out.print("Enter the Hr Id :");
        int hrId = scanner.nextInt();
        Queue<Candidate> candidates = candidateModel.getCandidateList(hrId);
        for (Candidate candidate : candidates) {
            System.out.println("Candidate Name :" + candidate.getCandidateName() + " Candidate Email :"
                    + candidate.getCandidateEmail() + " Candidate Qualification :"
                    + candidate.getCandidateQualification());
        }
    }

    public void removeCandidate() {
        printHR();
        System.out.print("Enter the Hr Id :");
        int hrId = scanner.nextInt();
        candidateModel.removeCandidate(hrId);
    }

    public void showMessage(String string) {
        System.out.println(string);
    }

    public void showAllCandidate() {
        printCandidates();
    }
}
