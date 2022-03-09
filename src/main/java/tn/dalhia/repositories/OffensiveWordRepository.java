package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.OffensiveWord;

@Repository
public interface OffensiveWordRepository extends JpaRepository<OffensiveWord, Long> {
}
