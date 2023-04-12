package com.example.ismpack.Models;

public class ViewPostModel {
    private String commentBody,commentedBy;
    private long commentedAt;

    public ViewPostModel(String commentBody, String commentedBy, long commentedAt) {
        this.commentBody = commentBody;
        this.commentedBy = commentedBy;
        this.commentedAt = commentedAt;
    }

    public ViewPostModel() {
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(String commentedBy) {
        this.commentedBy = commentedBy;
    }

    public long getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(long commentedAt) {
        this.commentedAt = commentedAt;
    }
}
