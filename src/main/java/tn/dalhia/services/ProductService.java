package tn.dalhia.services;

import tn.dalhia.request.ProductRequestModel;
import tn.dalhia.shared.dto.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductRequestModel product);

	ProductDto updateProduct(ProductRequestModel product, String id);

	ProductDto getProductById(String id);

	void deleteProduct(String id);

}
