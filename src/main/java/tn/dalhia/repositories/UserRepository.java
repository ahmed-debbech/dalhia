package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
}
