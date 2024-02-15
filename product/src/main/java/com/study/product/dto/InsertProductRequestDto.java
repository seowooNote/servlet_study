package com.study.product.dto;

import com.study.product.vo.ProductVo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InsertProductRequestDto {
	private String productName;
	private int productPrice;
	private String productSize;
	
	public ProductVo toVo() {
		return ProductVo.builder()
				.productName(productName)
				.productPrice(productPrice)
				.productSize(productSize)
				.build();
	}
}
