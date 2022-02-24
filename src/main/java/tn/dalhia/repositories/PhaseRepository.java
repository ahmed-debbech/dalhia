package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Phase;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {
}
