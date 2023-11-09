package kr.co.project.f1korea.repository;

import kr.co.project.f1korea.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
