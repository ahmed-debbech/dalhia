package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	UserEntity findBySubscriptionsId(Long id);
}
