//package com.study.product.servlet;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//import com.study.product.dao.ProductDao;
//import com.study.product.entity.Product;
//
//@WebServlet("/product")
//public class InsertProductServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    
//    public InsertProductServlet() {
//        super();
//    }
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// request 받아오기
//		// 1) JSON 문자열형 데이터 쌓기
//		StringBuilder stringBuilder = new StringBuilder();
//		String readData = null;
//		
//		BufferedReader bufferedReader = request.getReader();
//		
//		while((readData = bufferedReader.readLine()) != null) {
//			stringBuilder.append(readData);
//		}
//		
//		// 2) JSON -> JAVA 객체로 변환
//		Gson gson = new Gson();
//		Product product = gson.fromJson(stringBuilder.toString(), Product.class);
//		System.out.println(product);
//		
//		ProductDao productDao = ProductDao.getInstance();
//		Product findProduct = productDao.findByProductName(product.getName()); 
//		System.out.println(findProduct);
//		
//		if(findProduct != null) {
//			Map<String, Object> errorMap = new HashMap<>();
//			errorMap.put("errorMessage", "이미 등록된 이름입니다.");
//			response.setStatus(400);
//			response.setContentType("application/json");
//			response.getWriter().println(gson.toJson(errorMap));
//			return;
//		}
//		
//		int successCount = productDao.saveProduct(product);
//		response.setStatus(201);
//		
//	}
//
//}
