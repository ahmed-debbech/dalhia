package tn.dalhia.repositories;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Appointment;
import tn.dalhia.entities.AppointmentRate;
import tn.dalhia.entities.AppointmentReport;
import tn.dalhia.entities.User;



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

	@Modifying
	@Transactional
	@Query(value="DELETE FROM appointment WHERE appointment.user_id = :usrId",nativeQuery=true)
	public void DeleteAppByUsrId(@Param("usrId") Long usrId);


	@Query(value ="SELECT * FROM appointment WHERE appointment.sender_id =:senderId",nativeQuery=true)
    public List<Appointment> getMyAppointments(@Param("senderId") Long senderId);
	
	@Query(value ="SELECT * FROM user WHERE id=:expertId",nativeQuery=true)
    public List<User> getExpertDetails(@Param("expertId") Long expertId);
	
	
	
	
}

