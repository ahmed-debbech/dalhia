package tn.dalhia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.*;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {

}
