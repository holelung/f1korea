package kr.co.project.f1korea.service;

import kr.co.project.f1korea.domain.Comment;
import kr.co.project.f1korea.domain.Post;
import kr.co.project.f1korea.domain.User;
import kr.co.project.f1korea.dto.CommentDto;
import kr.co.project.f1korea.repository.CommentRepository;
import kr.co.project.f1korea.repository.PostRepository;
import kr.co.project.f1korea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    //댓글 저장
    public Comment saveComment(CommentDto commentDto) {
        Comment comment = new Comment();

        // 여기서는 Post와 User 엔티티를 찾는 로직이 필요합니다.
        // 예를 들어, findById 메서드를 사용해 Post와 User 엔티티를 가져올 수 있습니다.
        // 이 부분은 실제 어플리케이션의 구조에 따라 달라질 수 있습니다.
        Post post = postRepository.findById(commentDto.getPostId()).orElse(null);
        User user = userRepository.findById(commentDto.getUserId()).orElse(null);

        comment.setPostId(post);
        comment.setUserId(user);
        comment.setContent(commentDto.getContent());

        return commentRepository.save(comment);
    }
    public void deleteComment(Long commentId) {
        // 댓글 ID로 댓글 찾기
        Comment comment = commentRepository.findById(commentId).orElse(null);

        // 댓글이 존재하면 삭제
        if (comment != null) {
            commentRepository.delete(comment);
        }
    }


    public List<Comment> getCommentsByPostId(Post postId) {
        return commentRepository.findByPostId(postId);
    }
}
