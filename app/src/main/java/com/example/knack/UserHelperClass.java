package com.example.knack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserHelperClass {
    String fullName;
    String email;
    String phone;
    String userID;
    boolean accountVerified;
    ArrayList<String> connections;
    ArrayList<String> requests;
    String skills;

    public UserHelperClass(){

    }

    public UserHelperClass(String fullName, String email, String phone,String userID,boolean isAccountVerified,
                           ArrayList<String> connections,ArrayList<String> requests,String skills) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.userID=userID;
        this.connections=connections;
        this.accountVerified=isAccountVerified;
        this.requests=requests;
        this.skills=skills;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public ArrayList<String> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<String> requests) {
        this.requests = requests;
    }

    public ArrayList<String> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<String> connections) {
        this.connections = connections;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        accountVerified = accountVerified;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
