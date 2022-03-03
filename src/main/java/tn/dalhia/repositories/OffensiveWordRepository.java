package tn.dalhia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.OffensiveWord;

@Repository
public interface OffensiveWordRepository extends CrudRepository<OffensiveWord,Long> {

}
