package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.CommandProduct;

@Repository
public interface CommandProductRepository extends JpaRepository<CommandProduct,Long>{

}
