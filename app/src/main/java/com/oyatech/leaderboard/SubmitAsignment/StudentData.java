package com.oyatech.leaderboard.SubmitAsignment;

public class StudentData {
    String firstName;
    String lastName;
    String email;
    String gitHubLink;

    public StudentData(String pFirstName, String pLastName, String pEmail, String pGitHubLink) {
        firstName = pFirstName;
        lastName = pLastName;
        email = pEmail;
        gitHubLink = pGitHubLink;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String pFirstName) {
        firstName = pFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String pLastName) {
        lastName = pLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String pEmail) {
        email = pEmail;
    }

    public String getGitHubLink() {
        return gitHubLink;
    }

    public void setGitHubLink(String pGitHubLink) {
        gitHubLink = pGitHubLink;
    }
}
