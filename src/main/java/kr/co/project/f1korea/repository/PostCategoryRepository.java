package kr.co.project.f1korea.repository;

import kr.co.project.f1korea.domain.PostCategory;
import kr.co.project.f1korea.domain.PostCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryRepository extends JpaRepository<PostCategory, PostCategoryId> {
}
