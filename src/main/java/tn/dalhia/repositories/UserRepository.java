package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	@Query(value="SELECT u, COUNT(activity) AS count FROM User u WHERE u.role ='ASSOCIATION' GROUP BY u.activity",nativeQuery=false)
	public List<User> getAssociationsPerActivityCount();


	User findByEmail(String email);
	User findByUserId(String userId);
	User findBySubscriptionsId(Long id);
	User findUserByEmailVerificationToken(String token);

}
