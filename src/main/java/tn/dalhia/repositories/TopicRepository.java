package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
