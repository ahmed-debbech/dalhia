package tn.dalhia.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Appointment;



@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Integer> {

	@Query(value ="SELECT appointment.user_id FROM appointment GROUP BY appointment.user_id ORDER BY COUNT(appointment.app_id) ASC LIMIT 1",nativeQuery=true)
    public Long getLeastVisitedExpertId();

	
	@Query(value ="SELECT appointment.user_id FROM appointment GROUP BY appointment.user_id ORDER BY COUNT(appointment.app_id) DESC LIMIT 1",nativeQuery=true)
    public Long getMostVisitedExpertId();
	
	@Query(value ="SELECT COUNT(appointment.app_id) FROM appointment GROUP BY appointment.user_id ORDER BY COUNT(appointment.app_id) DESC LIMIT 1",nativeQuery=true)
    public int getMostVisitedExpertAppNbr();

	@Query(value ="SELECT COUNT(appointment.app_id) FROM appointment GROUP BY appointment.user_id ORDER BY COUNT(appointment.app_id) ASC LIMIT 1",nativeQuery=true)
    public int getLeastVisitedExpertAppNbr();
	
	
}

