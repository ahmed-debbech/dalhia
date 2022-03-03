package tn.dalhia.services.implementations;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.Product;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.repositories.ProductRepository;
import tn.dalhia.request.ProductRequestModel;
import tn.dalhia.response.ErrorMessages;
import tn.dalhia.services.ProductService;
import tn.dalhia.shared.dto.ProductDto;
import tn.dalhia.shared.dto.Utils;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepo;
	@Autowired
	Utils utils;
	
	@Override
	public ProductDto createProduct(ProductRequestModel product) {
		Product productEntity = new Product();
		ProductDto returnValue = new ProductDto();
		
		BeanUtils.copyProperties(product,productEntity);
		productEntity.setProductId(utils.generateProductId(30));
		Product createProdcut = productRepo.save(productEntity);
		BeanUtils.copyProperties(createProdcut,returnValue);
		return returnValue;
	}

	@Override
	public ProductDto updateProduct(ProductRequestModel product, String id) {
		ProductDto returnValue = new ProductDto();
		Product productEntity = productRepo.findByProductId(id);
		if (productEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		productEntity.setDescription(product.getDescription());
		productEntity.setPrice(product.getPrice());
		productEntity.setTitle(product.getTitle());
		
		Product updateProduct = productRepo.save(productEntity);
		BeanUtils.copyProperties(updateProduct,returnValue);
		return returnValue;
	}

	@Override
	public ProductDto getProductById(String id) {
		ProductDto returnValue = new ProductDto();
		Product product = productRepo.findByProductId(id);
		if (product == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		BeanUtils.copyProperties(product,returnValue);
		return returnValue;
	}

	@Override
	public void deleteProduct(String id) {
		Product product = productRepo.findByProductId(id);
		if (product == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		productRepo.delete(product);
	}

}
