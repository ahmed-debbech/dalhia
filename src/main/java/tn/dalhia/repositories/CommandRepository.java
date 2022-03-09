package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Command;

@Repository
public interface CommandRepository extends JpaRepository<Command,Long> {
	Command findByCommandId(String id) ;
}
