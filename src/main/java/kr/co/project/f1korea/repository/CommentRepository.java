package kr.co.project.f1korea.repository;

import kr.co.project.f1korea.domain.Comment;
import kr.co.project.f1korea.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Post postId);
}
