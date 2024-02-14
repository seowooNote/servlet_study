package com.study.servlet_study.service;

import com.study.servlet_study.entity.Product;
import com.study.servlet_study.repository.ProductRepository;

public class ProductService {
	private static ProductService instance;
	private ProductRepository productRepository; 
	
	private ProductService() {
		productRepository = ProductRepository.getInstance();
	}
	
	public static ProductService productService() {
		if(instance == null) {
			instance = new ProductService();
		}
		return instance;
	}
	
	public int addProduct(Product product) {
		return productRepository.saveProduct(product);
	}
	
	public Product getProduct(String productName) {
		return productRepository.findProductByProductName(productName);
	}
	
//	public boolean findDuplication(Product product) {
//		if(productRepository.findProductByProductName(product.getProductName()) == null) {
//			return false;
//		}
//		return true;
//	}

}
