package com.example.demo_project.service;

import com.example.demo_project.model.dto.CommentAddDTO;
import com.example.demo_project.model.entity.Comment;

import java.util.List;


public interface CommentService {

    Comment addComment(CommentAddDTO commentAddDTO);
    List<Comment> getComments();
    void deleteComment(Long id);
}