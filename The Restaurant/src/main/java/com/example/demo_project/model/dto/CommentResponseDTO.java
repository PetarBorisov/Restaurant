package com.example.demo_project.model.dto;

public class CommentResponseDTO {

    private Long id;
    private String text;
    private String userName;
    private String userEmail;

    public CommentResponseDTO(Long id, String text, String userName) {
        this.id = id;
        this.text = text;
        this.userName = userName;
    }

    public CommentResponseDTO() {

    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
