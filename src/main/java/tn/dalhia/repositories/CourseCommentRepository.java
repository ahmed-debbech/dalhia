package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.CourseComment;

@Repository
public interface CourseCommentRepository extends JpaRepository<CourseComment, Long> {
}
