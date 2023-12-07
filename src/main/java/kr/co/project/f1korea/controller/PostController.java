package kr.co.project.f1korea.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.project.f1korea.domain.Post;
import kr.co.project.f1korea.dto.AddPostRequest;
import kr.co.project.f1korea.dto.UpdatePostRequest;
import kr.co.project.f1korea.dto.UserRequest;
import kr.co.project.f1korea.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PostController {
    @Autowired
    PostService postService;


    // 클래스 내부
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    // 메서드 내부에서 userId 확인

    @PostMapping("/api/posts") // 이 주소로 Post 요청을 보내면 아래 메서드 실행
    public ResponseEntity<Post> addPost(@RequestBody AddPostRequest request, HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession(true);
        String userId = (String) session.getAttribute("userId");
        if(userId == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Post savedPost = postService.save(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedPost);
    }

    @GetMapping("/api/posts/{id}") // id
    public ResponseEntity<Post> findPost(@PathVariable long id) { // url에 들어온 id 값을 변수로 받아오기 -> @PathVariable
        Post post = postService.findOne(id);
        return ResponseEntity.ok().body(post);
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable long id,  HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(true);
        String userId = (String) session.getAttribute("userId");

        if(userId == null) {
            return ResponseEntity.badRequest().body(null);
        }
        //id와 session 아이디 대조 필요

        postService.delete(id);
        return ResponseEntity.ok().build();
    }

    // 해당 id 값에 따른 글 목록 수정
    @PutMapping("/api/posts/{id}")
    public ResponseEntity<Post> updateArticle(@PathVariable long id, @RequestBody UpdatePostRequest updatePost) {
        Post post = postService.update(id, updatePost);
        return ResponseEntity.ok().body(post);
    }
}
