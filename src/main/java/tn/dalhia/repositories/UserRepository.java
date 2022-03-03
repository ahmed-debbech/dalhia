package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
	User findByUserId(String userId);
	User findBySubscriptionsId(Long id);
	User findUserByEmailVerificationToken(String token);
}
