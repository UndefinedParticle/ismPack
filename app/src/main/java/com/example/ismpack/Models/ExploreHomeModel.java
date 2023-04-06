package com.example.ismpack.Models;

public class ExploreHomeModel {
    private String postId,postItemName,postImage,postedBy,postDescription,postUserId;
    private long postedAt;

    public ExploreHomeModel(String postId,String postItemName, String postImage, String postedBy, String postDescription, long postedAt) {
        this.postItemName = postItemName;
        this.postId = postId;
        this.postImage = postImage;
        this.postedBy = postedBy;
        this.postDescription = postDescription;
        this.postedAt = postedAt;
    }

    public ExploreHomeModel() {
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public long getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(long postedAt) {
        this.postedAt = postedAt;
    }

    public String getPostItemName() {
        return postItemName;
    }

    public void setPostItemName(String postItemName) {
        this.postItemName = postItemName;
    }

    public String getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(String postUserId) {
        this.postUserId = postUserId;
    }


    //int profile,postImage,save;
    //String profileName,about,like,comment,share;



}
