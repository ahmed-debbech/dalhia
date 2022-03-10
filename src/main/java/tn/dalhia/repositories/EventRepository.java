package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Event;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface EventRepository extends JpaRepository<Event, Long> {
   // @Query(value="SELECT SUM(amount) FROM donation WHERE event_id= :eventId",nativeQuery=true)
   // public Event GetAllColllectedAmount(Long id);
    @Query(value ="SELECT event.id FROM event WHERE event.collected_amount = (select MAX(collected_amount) from event) LIMIT 1",nativeQuery=true)
    public Long getMostEventSuccess();
}
