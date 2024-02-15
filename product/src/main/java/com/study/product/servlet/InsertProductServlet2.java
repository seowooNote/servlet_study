package com.study.product.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.dto.InsertProductRequestDto;
import com.study.product.service.ProductService;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;

@WebServlet("/product")
public class InsertProductServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
       
    public InsertProductServlet2() {
        super();
        productService = ProductService.getInstance();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InsertProductRequestDto requestDto = RequestUtil.convertJsonData(request, InsertProductRequestDto.class);
		
		if(productService.isDuplicatedProductName(requestDto.getProductName())) {
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("errorMessage", "이미 등록된 상품명입니다.");
			ResponseEntity.ofJson(response, 400, responseMap);
			return;
		};
		
		ResponseEntity.ofJson(response, 201, productService.addProduct(requestDto));
	}
	

}
