package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
