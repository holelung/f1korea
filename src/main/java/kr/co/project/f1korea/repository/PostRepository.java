package kr.co.project.f1korea.repository;

import kr.co.project.f1korea.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
