package kr.co.project.f1korea.repository;

import kr.co.project.f1korea.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
