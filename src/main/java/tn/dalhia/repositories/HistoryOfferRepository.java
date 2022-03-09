package tn.dalhia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.HistoryOffer;
@Repository
public interface HistoryOfferRepository extends CrudRepository<HistoryOffer, Long> {
}
