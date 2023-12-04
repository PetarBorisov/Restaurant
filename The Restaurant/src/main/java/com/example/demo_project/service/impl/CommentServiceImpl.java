package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.CommentAddDTO;
import com.example.demo_project.model.entity.Comment;
import com.example.demo_project.repository.CommentRepository;
import com.example.demo_project.repository.UserRepository;
import com.example.demo_project.service.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public Comment addComment(CommentAddDTO commentAddDTO) {
        Comment comment = new Comment();
        comment.setText(commentAddDTO.getText());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        comment.setUserEmail(userEmail);

        return commentRepository.save(comment);
    }


    @Override
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}