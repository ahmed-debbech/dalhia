package tn.dalhia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Report;
import tn.dalhia.entities.User;
import tn.dalhia.entities.enumerations.ReportCategory;




@Repository
public interface ReportRepository extends CrudRepository<Report,Integer> {

	@Query(value = "SELECT user FROM User user WHERE user.role LIKE 'ASSOCIATION' AND user.activity= :category")
	public List<User> getSuggestions(@Param("category")ReportCategory RpCat);
}
