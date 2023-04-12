package com.example.ismpack.Models;

public class SearchModel {

    String postedBy,postItemName,postImage,postDescription,postUserId,postAt;


    public SearchModel(String postedBy, String postItemName, String postImage, String postDescription, String postUserId, String postAt) {
        this.postedBy = postedBy;
        this.postItemName = postItemName;
        this.postImage = postImage;
        this.postDescription = postDescription;
        this.postUserId = postUserId;
        this.postAt = postAt;
    }

    public SearchModel() {
    }




    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostItemName() {
        return postItemName;
    }

    public void setPostItemName(String postItemName) {
        this.postItemName = postItemName;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(String postUserId) {
        this.postUserId = postUserId;
    }

    public String getPostAt() {
        return postAt;
    }

    public void setPostAt(String postAt) {
        this.postAt = postAt;
    }
}
