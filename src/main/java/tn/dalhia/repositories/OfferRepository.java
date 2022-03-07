package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.*;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

    @Query("SELECT o FROM Offer o WHERE o.title like :SpecialiteUser")
    List<Offer> findAllOfferBySpecialty(
            @Param("SpecialiteUser") String SpecialiteUser);


    @Query("SELECT o FROM Offer o "
            + "WHERE lower(o.title) like lower(:searchText) "
            + "OR lower(o.description) like lower(:searchText)  ")
            public List<Offer> searchOfferByText(@Param("searchText") String searchText);

}
