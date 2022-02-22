package tn.dalhia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Request;



@Repository
public interface RequestRepository extends CrudRepository<Request,Integer> {

}
