package kr.co.project.f1korea.service;

import jakarta.transaction.Transactional;
import kr.co.project.f1korea.domain.Post;
import kr.co.project.f1korea.domain.User;
import kr.co.project.f1korea.dto.AddPostRequest;
import kr.co.project.f1korea.dto.UpdatePostRequest;
import kr.co.project.f1korea.repository.PostRepository;
import kr.co.project.f1korea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post save(AddPostRequest request, String userId) {
        System.out.println(userId);
        Post post = request.toEntity();
        User user = userRepository.findByEmail(userId);
        System.out.println("user:"+user.getEmail());
        post.setUserId(user);
        return postRepository.save(post);
    }

    public List<Post> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
    public Post findOne(long id) {
        Post post = postRepository.findById(id).orElseThrow(); // 찾아내지 못할 경우 에러가 발생하게 처리 -> orElseThrow
        return post;
    }
    public void delete(long id) {
        postRepository.deleteById(id);
    }
    @Transactional // 어노테이션을 사용해야 업데이트가 진행된 후 데이터베이스에 적용, import 할 때 spring에 있는 것을 사용해야 함
    public Post update(long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id).orElseThrow();
        post.update(request.getTitle(), request.getContent()); // 오류 무시해도 됨
        return post;
    }


}
