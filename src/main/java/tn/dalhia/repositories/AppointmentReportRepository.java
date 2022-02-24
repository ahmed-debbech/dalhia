package tn.dalhia.repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.AppointmentReport;



@Repository
public interface AppointmentReportRepository extends CrudRepository<AppointmentReport,Long> {

}

