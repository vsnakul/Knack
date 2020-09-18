package com.example.knack;

public class UserHelperClass {
    String fullName;
    String email;
    String phone;
    String userID;
    boolean accountVerified;
    public UserHelperClass(){

    }

    public UserHelperClass(String fullName, String email, String phone,String userID,boolean isAccountVerified) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.userID=userID;
        this.accountVerified=isAccountVerified;
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
