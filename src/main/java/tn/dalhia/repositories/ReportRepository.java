package tn.dalhia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Report;



@Repository
public interface ReportRepository extends CrudRepository<Report,Integer> {

}
