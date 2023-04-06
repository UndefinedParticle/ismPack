package com.example.ismpack.Models;

public class SearchModel {

    private String postedBy,postItemName,postImage,postDescription;


    public SearchModel(String postedBy, String postItemName, String postImage , String postDescription) {
        this.postedBy = postedBy;
        this.postItemName = postItemName;
        this.postImage = postImage;
        this.postDescription = postDescription;
    }

    public SearchModel() {
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

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
}
