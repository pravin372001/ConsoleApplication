package com.zsgs.interviewpanel.model;

import com.zsgs.interviewpanel.datalayer.InterViewPanelDB;

public class Candidate {
    private int candidateId;
    private String candidateName;
    private String candidateEmail;
    private String candidateMobile;
    private String candidateQualification;
    private int hrId;

    public Candidate(String candidateName, String candidateEmail, String candidateMobile,
            String candidateQualification, int hrId) {
        this.candidateId = InterViewPanelDB.getInstance().getCandidateList(hrId).size() + 1;
        this.candidateName = candidateName;
        this.candidateEmail = candidateEmail;
        this.candidateMobile = candidateMobile;
        this.candidateQualification = candidateQualification;
        this.hrId = hrId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidateMobile() {
        return candidateMobile;
    }

    public void setCandidateMobile(String candidateMobile) {
        this.candidateMobile = candidateMobile;
    }

    public String getCandidateQualification() {
        return candidateQualification;
    }

    public void setCandidateQualification(String candidateQualification) {
        this.candidateQualification = candidateQualification;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

}
