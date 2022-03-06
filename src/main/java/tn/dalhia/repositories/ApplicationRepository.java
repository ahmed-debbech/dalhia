package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.*;

import java.util.List;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {

 /*   @Query("select a from Application a where a.etat == 1")
    List<Application> findAvailableApplications();
    @Query("select a from Application a where a.etat == 0")
    List<Application> findNotAvailableApplications();*/
}
