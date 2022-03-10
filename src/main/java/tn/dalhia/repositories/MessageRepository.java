package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
