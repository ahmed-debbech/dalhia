package tn.dalhia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.dalhia.request.ProductRequestModel;
import tn.dalhia.response.OperationStatusModel;
import tn.dalhia.response.ProductRest;
import tn.dalhia.response.RequestOperationName;
import tn.dalhia.response.RequestOperationStatus;
import tn.dalhia.services.ProductService;
import tn.dalhia.shared.dto.ProductDto;

@RestController
@RequestMapping("products")
@Api(tags ="Gestion des produits")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping
	public ProductRest createProduct(@RequestBody ProductRequestModel product, Authentication authentification) {
	ModelMapper modelMapper = new ModelMapper();
	ProductDto createProduct = productService.createProduct(product,authentification);
	ProductRest returnValue = modelMapper.map(createProduct, ProductRest.class);
	return returnValue;
	}
	@PutMapping("/{id}")
	public ProductRest updateProduct(@RequestBody ProductRequestModel product ,@PathVariable String id, Authentication authentification) {

		
		ModelMapper modelMapper = new ModelMapper();
		ProductDto updateProduct = productService.updateProduct(product,id,authentification);
		ProductRest returnValue = modelMapper.map(updateProduct, ProductRest.class);
		
		return returnValue;
	}
	
	@GetMapping("/{id}")
	public ProductRest getProduct(@PathVariable String id, Authentication authentification) {
		ModelMapper modelMapper = new ModelMapper();
		ProductDto product = productService.getProductById(id,authentification);
		ProductRest returnValue = modelMapper.map(product, ProductRest.class);
		return returnValue;
	}
	
	@DeleteMapping("/{id}")
	public OperationStatusModel deleteProduct(@PathVariable String id, Authentication authentification) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		productService.deleteProduct(id,authentification);
		
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
}
