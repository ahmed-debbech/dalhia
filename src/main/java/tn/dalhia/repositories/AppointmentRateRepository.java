package tn.dalhia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.AppointmentRate;



@Repository
public interface AppointmentRateRepository extends CrudRepository<AppointmentRate,Long> {

	@Query(value ="SELECT * FROM appointment_rate WHERE id=:appRateId ",nativeQuery=true)
	public List <AppointmentRate> getAppointmentRate(@Param("appRateId") Long appRateId);
	
}
