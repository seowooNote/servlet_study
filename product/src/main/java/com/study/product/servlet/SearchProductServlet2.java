package com.study.product.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.service.ProductService;
import com.study.product.utils.ResponseEntity;

@WebServlet("/products")
public class SearchProductServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService;
	
    public SearchProductServlet2() {
        super();
        productService = ProductService.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponseEntity.ofJson(response, 200, productService.searchProducts());
	}

}
