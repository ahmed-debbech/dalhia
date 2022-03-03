package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
