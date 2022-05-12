package tn.dalhia.services;

import java.util.List;

import org.springframework.security.core.Authentication;

import tn.dalhia.entities.Product;
import tn.dalhia.request.ProductRequestModel;
import tn.dalhia.shared.dto.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductRequestModel product, Authentication authentification);

	ProductDto updateProduct(ProductRequestModel product, String id, Authentication authentification);

	ProductDto getProductById(String id, Authentication authentification);

	void deleteProduct(String id, Authentication authentification);
	
	List<Product> getProducts( Authentication authentification);

}
