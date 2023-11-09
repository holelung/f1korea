package kr.co.project.f1korea.repository;

import kr.co.project.f1korea.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
