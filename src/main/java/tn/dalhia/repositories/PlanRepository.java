package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {

}
