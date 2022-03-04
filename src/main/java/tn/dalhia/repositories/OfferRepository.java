package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.*;
@Repository
public interface OfferRepository extends JpaRepository <Offer, Long> {

}
