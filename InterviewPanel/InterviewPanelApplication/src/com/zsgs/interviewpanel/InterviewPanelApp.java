package com.zsgs.interviewpanel;

import com.zsgs.interviewpanel.datalayer.InterViewPanelDB;
import com.zsgs.interviewpanel.login.LoginView;

public class InterviewPanelApp {

    private static InterviewPanelApp interviewPanel;
    private String appName = "Interview Panel Application";

    private String appVersion = "0.1.0";

    private InterviewPanelApp() {

    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public static InterviewPanelApp getInstance() {
        if (interviewPanel == null) {
            interviewPanel = new InterviewPanelApp();
        }
        return interviewPanel;
    }

    private void init() {
        LoginView loginView = new LoginView();
        loginView.init();
    }

    public static void main(String[] args) {
        System.out.println(
                InterviewPanelApp.getInstance().getAppName() + " " + InterviewPanelApp.getInstance().getAppVersion());
        InterviewPanelApp.getInstance().getFromDB();
        InterviewPanelApp.getInstance().init();

    }

    private void getFromDB() {
        InterViewPanelDB.getInstance().deserializeFromDb();
    }
}
