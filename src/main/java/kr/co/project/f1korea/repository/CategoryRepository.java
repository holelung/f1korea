package kr.co.project.f1korea.repository;

import kr.co.project.f1korea.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
