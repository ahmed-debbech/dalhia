package tn.dalhia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

	@Query(value="SELECT u, COUNT(activity) AS count FROM User u WHERE u.role ='ASSOCIATION' GROUP BY u.activity",nativeQuery=false)
	public List<User> getAssociationsPerActivityCount();
	
	
}
