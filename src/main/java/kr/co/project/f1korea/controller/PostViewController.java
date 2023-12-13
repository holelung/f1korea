package kr.co.project.f1korea.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.project.f1korea.domain.Post;
import kr.co.project.f1korea.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostViewController {

    @Autowired
    PostService postService;

    //글 목록 불러오기
    //카테고리 /posts/(category)/  -->추가해야함
    @GetMapping("/posts")
    public ModelAndView getPost(@RequestParam(value="page", defaultValue="0") int page) {
        ModelAndView mav = new ModelAndView();

        Page<Post> posts = this.postService.getList(page);

        mav.addObject("posts",posts);
        mav.setViewName("community");

        return mav;
    }


    //검색
    @GetMapping("/posts/search")
    public ModelAndView searchPosts(@RequestParam String query, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 10; // 페이지 당 게시글 수
        PageRequest pageRequest = PageRequest.of(page, pageSize);

        // 검색 로직 구현. 예: 게시글 제목 또는 내용에서 검색어(query)를 포함하는 게시글 찾기
        Page<Post> searchResults = postService.searchByQuery(query, pageRequest);

        ModelAndView mav = new ModelAndView();
        mav.addObject("posts", searchResults);

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
