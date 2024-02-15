package com.study.product.vo;

import com.study.product.dto.InsertProductResponseDto;
import com.study.product.dto.SearchProductResponseDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductVo {
	private int productId;
	private String productName;
	private int productPrice;
	private String productSize;
	
	public InsertProductResponseDto toInsertDto(int successCount) {
		return InsertProductResponseDto.builder()
				.successCount(successCount)
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productSize(productSize)
				.build();
	}
	
	public SearchProductResponseDto toSearchDto() {
		return	SearchProductResponseDto.builder()
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productSize(productSize)
				.build();
	}
	
}
