package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.dalhia.entities.Donation;
@RepositoryRestResource
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query(value ="SELECT donation.id FROM donation WHERE donation.amount = (select MAX(amount) from donation) LIMIT 1",nativeQuery=true)
    public Long getBiggerDonationDone();
}