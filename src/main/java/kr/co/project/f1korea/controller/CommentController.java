package kr.co.project.f1korea.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.project.f1korea.domain.Comment;
import kr.co.project.f1korea.domain.Post;
import kr.co.project.f1korea.dto.CommentDto;
import kr.co.project.f1korea.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/api/comments")
    public ResponseEntity<Comment> addComment(@RequestBody CommentDto commentDto, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(true);
        String userId = (String) session.getAttribute("userId");
        if(userId == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Comment comment = commentService.saveComment(commentDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }



}
