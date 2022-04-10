package pro.sky.java.course3.work6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.course3.work6.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long id);
}
