package com.example.knack;

public class UserHelperClass {
    String fullName;
    String email;
    String phone;
    boolean isAccountVerified;
    String userID;
    public UserHelperClass(){

    }

    public UserHelperClass(String fullName, String email, String phone, boolean isAccountVerified,String userID) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.isAccountVerified = isAccountVerified;
        this.userID=userID;
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

    public boolean isAccountVerified() {
        return isAccountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        isAccountVerified = accountVerified;
    }
}
