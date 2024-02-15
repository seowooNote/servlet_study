package com.study.product.service;

//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.study.product.dao.ProductDao2;
import com.study.product.dto.InsertProductRequestDto;
import com.study.product.dto.InsertProductResponseDto;
import com.study.product.dto.SearchProductResponseDto;
import com.study.product.vo.ProductVo;

public class ProductService {
	private static ProductService instance;
	private ProductDao2 productDao2;
	
	private ProductService() {
		productDao2 = ProductDao2.getInstance();
	}
	
	public static ProductService getInstance() {
		if(instance == null) {
			instance = new ProductService();
		}
		return instance;
	}
	
	public boolean isDuplicatedProductName(String productName) {
		return productDao2.findProductByProductName(productName) != null;
	}
	
	public InsertProductResponseDto addProduct(InsertProductRequestDto insertProductRequestDto) {
		ProductVo productVo = insertProductRequestDto.toVo();
		int successCount = productDao2.save(productVo);
		return productVo.toInsertDto(successCount);
	}
	
	public List<SearchProductResponseDto> searchProducts() {
//		List<SearchProductResponseDto> searchProductResponseDtos = new ArrayList<>();
//		List<ProductVo> productVos = productDao2.getProductList();
		
//		for(ProductVo productVo : productVos) {
//			searchProductResponseDtos.add(productVo.toSearchDto());
//		}
		
//		return searchProductResponseDtos;
		
		// stream 사용
		return productDao2.getProductList().stream()
				.map(vo -> vo.toSearchDto()) 			// .map(ProductVo::toSearchDto())
				.collect(Collectors.toList());
	}
}
