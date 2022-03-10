package tn.dalhia.repositories;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.AppointmentReport;



@Repository
public interface AppointmentReportRepository extends CrudRepository<AppointmentReport,Long> {

	//@Query(value ="SELECT appointment_report.appointment_id FROM appointment_report GROUP BY appointment_report.appointment_id",nativeQuery=true)
   // public Integer retrieveAppId(); 
	
	//@Query(value ="SELECT appointment.user_id FROM appointment WHERE appointment.app_id = :appId",nativeQuery=true)
	
	@Query(value ="SELECT DISTINCT user_id FROM appointment LIMIT 1",nativeQuery=true)
    public Long retrieveUserId();
	
	@Query(value ="SELECT COUNT(appointment_report_id) FROM appointment WHERE user_id = :usrId",nativeQuery=true)
	public int getAppReportCountbyUserId(@Param("usrId") Long usrId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE user SET `ban`= 1 WHERE id = :usrId",nativeQuery=true)

	public void updateBan(@Param("usrId") Long usrId);
	
}

