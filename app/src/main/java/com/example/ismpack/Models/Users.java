package com.example.ismpack.Models;

public class Users {

    String userName,admissionNo,email,pswd,userId;

    public Users(String userName, String admissionNo, String email, String pswd, String userId) {
        this.userName = userName;
        this.admissionNo = admissionNo;
        this.email = email;
        this.pswd = pswd;
        this.userId = userId;
    }

    public Users(){}

    // Register Constructor
    public Users(String userName, String admissionNo, String email, String pswd) {
        this.userName = userName;
        this.admissionNo = admissionNo;
        this.email = email;
        this.pswd = pswd;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
