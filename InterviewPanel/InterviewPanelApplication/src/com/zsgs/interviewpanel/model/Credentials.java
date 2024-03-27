package com.zsgs.interviewpanel.model;

import com.zsgs.interviewpanel.datalayer.InterViewPanelDB;

public class Credentials {
    private int credentialId;
    private int credentialIdCounter = InterViewPanelDB.getInstance().getCredentialList().size() + 1;
    private String username;
    private String password;

    public Credentials(String username, String password) {
        this.credentialId = credentialIdCounter;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public int getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(int credentialId) {
        this.credentialId = credentialId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
