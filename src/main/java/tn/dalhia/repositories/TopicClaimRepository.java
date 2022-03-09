package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.TopicClaim;

@Repository
public interface TopicClaimRepository extends JpaRepository<TopicClaim, Long> {
}
