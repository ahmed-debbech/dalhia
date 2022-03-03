package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
