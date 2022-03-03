package tn.dalhia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.AppointmentRate;



@Repository
public interface AppointmentRateRepository extends CrudRepository<AppointmentRate,Long> {

}
