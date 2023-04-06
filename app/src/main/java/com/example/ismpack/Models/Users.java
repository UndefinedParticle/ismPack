package com.example.ismpack.Models;

public class Users {

    private String userName,admissionNo,email,pswd,postItemName,postDescription,postImage;


    public Users(String userName, String admissionNo, String email, String pswd) {
        this.userName = userName;
        this.admissionNo = admissionNo;
        this.email = email;
        this.pswd = pswd;
    }

    public Users() {
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

    public String getPostItemName() {
        return postItemName;
    }

    public void setPostItemName(String postItemName) {
        this.postItemName = postItemName;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }
}
