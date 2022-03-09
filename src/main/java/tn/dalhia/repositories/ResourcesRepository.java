package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Resources;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Long> {
}
