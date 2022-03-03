package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.CommentReaction;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {
}
