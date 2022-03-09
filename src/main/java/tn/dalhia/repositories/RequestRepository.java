package tn.dalhia.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Request;
import tn.dalhia.entities.User;
import tn.dalhia.entities.enumerations.ReportCategory;




@Repository
public interface RequestRepository extends CrudRepository<Request,Integer> {

	@Transactional
	@Query(value="SELECT request.user_id FROM request JOIN user WHERE request.user_id=user.id AND user.activity = :Act GROUP BY request.user_id ORDER BY COUNT(request.request_id) DESC LIMIT 1",nativeQuery=true)
	public int findMostRequestedAssocPerActivity(@Param("Act")ReportCategory Act );
}
