package com.example.demo_project.web;

import com.example.demo_project.model.dto.CommentAddDTO;
import com.example.demo_project.model.dto.CommentResponseDTO;
import com.example.demo_project.model.entity.Comment;
import com.example.demo_project.repository.CommentRepository;
import com.example.demo_project.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "http://localhost:8080")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    public CommentController(CommentService commentService, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<CommentResponseDTO> addComment(@RequestBody CommentAddDTO commentAddDTO) {
        Comment comment = commentService.addComment(commentAddDTO);

        String userEmail = comment.getUserEmail();

        CommentResponseDTO responseDTO = new CommentResponseDTO();
        responseDTO.setId(comment.getId());
        responseDTO.setText(comment.getText());
        responseDTO.setUserEmail(userEmail);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> comments = commentService.getComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
            commentService.deleteComment(id);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
  }
}