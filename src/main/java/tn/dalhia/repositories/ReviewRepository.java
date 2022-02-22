package tn.dalhia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Review;



@Repository
public interface ReviewRepository extends CrudRepository<Review,Integer> {

}
