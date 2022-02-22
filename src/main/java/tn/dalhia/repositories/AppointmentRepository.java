package tn.dalhia.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Appointment;



@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Integer> {

}

