package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.ForumComment;

@Repository
public interface ForumCommentRepository extends JpaRepository<ForumComment, Long> {
}
