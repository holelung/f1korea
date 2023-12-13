package kr.co.project.f1korea.controller;


import kr.co.project.f1korea.domain.Comment;
import kr.co.project.f1korea.domain.Post;
import kr.co.project.f1korea.repository.PostRepository;
import kr.co.project.f1korea.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CommentViewController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/post/{postId}")
    public String viewPost(@PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId).orElse(null);
        List<Comment> comments = commentService.getCommentsByPostId(post);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        return "post"; // post.html을 렌더링
    }


}
