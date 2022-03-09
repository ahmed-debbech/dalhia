package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.JobCategory;

@Repository

public interface JobCategoryRepository extends JpaRepository<JobCategory, Long> {
}
