package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	Product findByProductId(String productId);
}
