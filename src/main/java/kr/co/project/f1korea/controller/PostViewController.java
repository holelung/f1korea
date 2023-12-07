package kr.co.project.f1korea.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.project.f1korea.domain.Post;
import kr.co.project.f1korea.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostViewController {

    @Autowired
    PostService postService;

    //글 목록 불러오기
    //카테고리 /posts/(category)/  -->추가해야함
    @GetMapping("/posts")
    public ModelAndView getPost(){
        ModelAndView mav = new ModelAndView();
        List<Post> posts = postService.findAll();
        for(Post post : posts){
            System.out.println(post.getUserId());
            if(post.getUserId()!=null){
                System.out.println(post.getUserId().getName());
            }
        }
        mav.addObject("posts",posts);
        mav.setViewName("community");

        return mav;
    }

    //글 불러오기
    @GetMapping("/posts/{id}")
    public ModelAndView getPost(@PathVariable long id){
        ModelAndView mav = new ModelAndView();
        Post post = postService.findOne(id);
        mav.addObject("post",post);
        mav.setViewName("post");

        return mav;
    }

    //글쓰기 화면 불러오기
    @GetMapping("/new-post")
    public String createPost(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(true);

        String userId=(String)session.getAttribute("userId");
        if(userId == null){
            return "login";
        }
        return "new-post";
    }

    //글 수정 화면 불러오기
    @GetMapping("/posts/modify/{id}")
    public ModelAndView modifyArticle(@PathVariable long id){
        ModelAndView mav = new ModelAndView();
        Post post=postService.findOne(id);
        mav.addObject("post", post);
        mav.setViewName("postModify");

        return mav;
    }
}
